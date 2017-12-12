package lab7.zad;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    View view = new View();
    DB database = new DB();
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){

        view.Scene1(primaryStage, database);

    }

}
