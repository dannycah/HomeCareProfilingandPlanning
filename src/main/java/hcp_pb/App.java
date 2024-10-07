package hcp_pb;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;

public class App extends Application {

    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file
    private static final String DB_INIT_CONFIG = "config/dbInitConfig.txt"; // Relative path to dbInitConfig file

    private static Scene scene;
    private String DB_URL; // Database URL
    private String DB_USER; // Database username
    private String DB_PASSWORD; // Database password
    private String DB_NAME; // Database name

    @Override
    public void start(Stage stage) {
        try {
            // Check if the program is already running
            if (isProgramAlreadyRunning()) {
                showAlert("Program is already running.", "Error", Alert.AlertType.ERROR);
                System.exit(0); // Terminate the application
            } else {
                updateIsOpenFile(); // Update the isOpen file to indicate the program is running
                Runtime.getRuntime().addShutdownHook(new Thread(this::closeProgram)); // Ensure the program updates isOpen file on exit

                // Load database configuration
                loadDatabaseConfig();

                // Check if MySQL server is running
                if (checkMySQLServer()) {
                    // Load and display the splash screen
                    scene = new Scene(loadFXML("SplashScreen"), 515, 336);
                    stage.setScene(scene);
                    stage.setTitle("Loading Home Care Profiling and Planning");
                    stage.setResizable(false);
                    stage.show();
                } else {
                    // Prompt the user to configure or exit if MySQL is not installed
                    showMySQLNotInstalledAlert();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            showAlert("An unexpected error occurred: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
            System.exit(1); // Exit the application on error
        }
    }

    private void loadDatabaseConfig() {
        try {
            File configFile = new File(DB_INIT_CONFIG);
            if (!configFile.exists()) {
                createConfigFile(); // Create initial config file if it doesn't exist
                createDefaultConfigFile(); // Create default values in config file
            }

            // Read the database configuration
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                DB_URL = reader.readLine(); // Read the first line (URL)
                DB_USER = reader.readLine(); // Read the second line (username)
                DB_PASSWORD = reader.readLine(); // Read the third line (password)
                DB_NAME = reader.readLine(); // Read the fourth line (dbname)
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error reading the database configuration.", "Error", Alert.AlertType.ERROR);
            System.exit(1); // Exit if an error occurs while reading the file
        }
    }

    private void createDefaultConfigFile() {
        try (FileWriter writer = new FileWriter(DB_CONFIG_FILE)) {
            writer.write("jdbc:mysql://localhost:3306/yourDatabaseName\n"); // Replace with your DB URL
            writer.write("yourUsername\n"); // Replace with your DB username
            writer.write("yourPassword\n"); // Replace with your DB password
            writer.write("yourDatabaseName\n"); // Replace with your DB password
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error creating default configuration file.", "Error", Alert.AlertType.ERROR);
            System.exit(1); // Exit if an error occurs while creating the file
        }
    }

    private void createConfigFile() {
        try (FileWriter writer = new FileWriter(DB_INIT_CONFIG)) {
            writer.write("jdbc:mysql://localhost:3306/\n"); // Placeholder DB URL
            writer.write("yourUsername\n"); // Placeholder DB username
            writer.write("yourPassword\n"); // Placeholder DB password
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error creating default configuration file.", "Error", Alert.AlertType.ERROR);
            System.exit(1); // Exit if an error occurs while creating the file
        }
    }

    private boolean isProgramAlreadyRunning() {
        try {
            File directory = new File("config/");
            if (!directory.exists()) {
                directory.mkdirs(); // Create the directories if they don't exist
            }

            File file = new File(IS_OPEN_FILE);
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
            }

            String content = new String(Files.readAllBytes(file.toPath()));
            return content.trim().equals("1"); // Check if the content is "1"
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Assume it's not running if we can't read the file
        }
    }

    private void updateIsOpenFile() {
        try (FileWriter writer = new FileWriter(IS_OPEN_FILE)) {
            writer.write("1"); // Update the file to indicate the program is running
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error updating the instance configuration.", "Error", Alert.AlertType.ERROR);
        }
    }

    private void closeProgram() {
        try (FileWriter writer = new FileWriter(IS_OPEN_FILE)) {
            writer.write("0"); // Update the file to indicate the program is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkMySQLServer() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            return connection != null; // Return true if the connection is successful
        } catch (SQLException e) {
            // Handle specific SQL exceptions
            if (e.getErrorCode() == 1045) { // Access denied
                // Handle access denied error
            } else {
                // Handle other SQL exceptions
            }
            return false; // Return false if connection fails
        }
    }

    private void showMySQLNotInstalledAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Configuration Error");
        alert.setHeaderText(null);
        alert.setContentText("MySQL Server might not be installed or configured.");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType configButton = new ButtonType("Configure");

        alert.getButtonTypes().setAll(exitButton, configButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == exitButton) {
            Platform.exit(); // Gracefully exit the application
        } else if (result.isPresent() && result.get() == configButton) {
            openConfiguration(); // Call a method to open the configuration window
        }
    }

    private void openConfiguration() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("configDB.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Database Configuration");
            stage.setScene(new Scene(root));
            stage.show(); // Show the configuration window
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Failed to load the configuration window.", "Error", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String message, String title, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait(); // Show alert and wait for user response
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load(); // Load the specified FXML file
    }

    public static void main(String[] args) {
        launch(); // Launch the JavaFX application
    }
}
