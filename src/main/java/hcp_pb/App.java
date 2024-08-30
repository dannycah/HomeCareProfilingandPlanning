package hcp_pb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
      scene = new Scene(loadFXML("SplashScreen"), 515, 336);
//        scene = new Scene(loadFXML("CreateCase"), 840, 606);
//         scene = new Scene(loadFXML("assessmentForm"), 1064, 600);

//scene = new Scene(loadFXML("adminDashboard"), 1064, 600);
//       scene = new Scene(loadFXML("welcomeHCP"), 670, 490);
//         scene = new Scene(loadFXML("NewClient"),520, 600);
        stage.setScene(scene);
        
      // stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Home Care Client Profiling and Planning");
        stage.setResizable(false);
        stage.show();
        
        
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}