package lab6.zad2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;

public class View{

    public void Scene1(Stage stage_, Model model_){

        StackPane root = new StackPane();

        Text tytul = new Text("Wprowadź współczynniki");
        Text x_4 = new Text("x^4");
        Text x_3 = new Text("x^3");
        Text x_2 = new Text("x^2");
        Text x_1 = new Text("x^1");
        Text x_0 = new Text("x^0");

        TextField x_4a = new TextField();
        TextField x_3b = new TextField();
        TextField x_2c = new TextField();
        TextField x_1d = new TextField();
        TextField x_0e = new TextField();
        x_4a.setMaxWidth(30);
        x_3b.setMaxWidth(30);
        x_2c.setMaxWidth(30);
        x_1d.setMaxWidth(30);
        x_0e.setMaxWidth(30);


        tytul.setTranslateY(-200);
        x_4a.setTranslateY(-170);
        x_4a.setTranslateX(-20);
        x_4.setTranslateY(-170);
        x_4.setTranslateX(20);

        x_3b.setTranslateY(-140);
        x_3b.setTranslateX(-20);
        x_3.setTranslateY(-140);
        x_3.setTranslateX(20);

        x_2c.setTranslateY(-110);
        x_2c.setTranslateX(-20);
        x_2.setTranslateY(-110);
        x_2.setTranslateX(20);

        x_1d.setTranslateY(-80);
        x_1d.setTranslateX(-20);
        x_1.setTranslateY(-80);
        x_1.setTranslateX(20);

        x_0e.setTranslateY(-50);
        x_0e.setTranslateX(-20);
        x_0.setTranslateY(-50);
        x_0.setTranslateX(20);



        TextField początek_przedziału = new TextField("Początek przedziału");
        TextField koniec_przedziału = new TextField("Koniec przedziału");
        TextField czestotliwosc = new TextField("Czestotliwosc");

        początek_przedziału.setMaxWidth(150);
        koniec_przedziału.setMaxWidth(150);
        czestotliwosc.setMaxWidth(150);

        początek_przedziału.setTranslateY(-20);
        koniec_przedziału.setTranslateY(10);
        czestotliwosc.setTranslateY(40);


        Button submit = new Button();
        submit.setText("Zatwierdź");
        submit.setTranslateY(80);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                    System.out.println("to"+x_4a.getCharacters()+"to");

                boolean wspolczynniki_ok = model_.PrzypiszWspolczynniki(x_4a.getCharacters(), x_3b.getCharacters(), x_2c.getCharacters(), x_1d.getCharacters(), x_0e.getCharacters());
                boolean przedzial_ok = model_.DodajPrzedziałiCzestotliwosc(początek_przedziału.getCharacters(), koniec_przedziału.getCharacters(), czestotliwosc.getCharacters());
                if(wspolczynniki_ok && przedzial_ok){
                    model_.FillXArray();
                    Scene2(stage_, model_);
                }else{
                    Text blad = new Text("Błędne dane");
                    blad.setTranslateY(120);
                    root.getChildren().add(blad);
                }
            }
        });

        root.getChildren().addAll(tytul,x_4a, x_4, x_3b, x_3, x_2c, x_2, x_1d, x_1, x_0e, x_0, początek_przedziału, koniec_przedziału, czestotliwosc, submit);
        Scene scene = new Scene(root, 500, 500);

        stage_.setTitle("Wielomiany");
        stage_.setScene(scene);

        stage_.show();
    }

    public void Scene2(Stage stage_, Model model_){

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(
                xAxis, yAxis);

        lineChart.setTitle(model_.a + "*x^4+"+model_.b+"*x^3+"+model_.c+"*x^2+"+model_.d+"*x^1+"+model_.e+"*x^0");
        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();

        for(int i=0; i<model_.x_array.size(); i++){
            series.getData().add(new XYChart.Data<Number, Number>(model_.x_array.get(i), model_.y_array.get(i)));
        }

        Scene scene2 = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage_.setScene(scene2);
        stage_.show();
    }

}
