module hcp_pb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.apache.pdfbox;
      requires java.desktop;

    opens hcp_pb to javafx.fxml;
    exports hcp_pb;
}
