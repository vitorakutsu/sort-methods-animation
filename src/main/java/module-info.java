module org.example.animacaoordenacao {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.animacaoordenacao to javafx.fxml;
    exports org.example.animacaoordenacao;
}