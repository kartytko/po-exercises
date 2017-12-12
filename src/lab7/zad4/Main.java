package lab7.zad4;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    DataBase database = new DataBase();
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage){




        database.SetUser("kartytko");
        database.SetPass("");

        //NALEŻY PODAĆ POPRAWNY NUMER PESEL!!!
        String PESEL = "";

        if(database.DodajPracownika(PESEL, 1000, true)){
            System.out.println("dodano pracownika");
        }else{
            System.out.println("nie dodano pracownika");
        }


        database.setBrutto(PESEL, 500);
        System.out.println("Kwota brutto to dla pracownika: "+ database.getBrutto(PESEL));
        System.out.println("Kwota netto to dla pracownika: "+ database.getNetto(PESEL));
        database.setBrutto("97032705586", 1500);
        System.out.println("Kwota brutto to dla pracownika: "+ database.getBrutto(PESEL));
        System.out.println("Kwota netto to dla pracownika: "+ database.getNetto(PESEL));

        if(database.UsunPracownika(PESEL)){
            System.out.println("usunięto pracownika");
        }

    }

}
