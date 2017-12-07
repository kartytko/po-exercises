package lab6.zad2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;


public class Wielomiany extends Application {

    View view = new View();
    Model model = new Model();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){

        view.Scene1(primaryStage, model);

    }
}
