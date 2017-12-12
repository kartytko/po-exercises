package lab7.zad;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Stack;

public class View {



    public void Scene1(Stage stage_, DB database_) {

        StackPane root = new StackPane();

        Text info = new Text("Połącz się z bazą danych");
        info.setTranslateY(-50);

        TextField user = new TextField("uzytkownik");
        TextField pass = new TextField("haslo");
        user.setTranslateY(-20);
        pass.setTranslateY(20);
        user.setMaxWidth(200);
        pass.setMaxWidth(200);

        Button submit = new Button();
        submit.setText("Zatwierdź");
        submit.setTranslateY(60);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*if(wspolczynniki_ok && przedzial_ok){
                    model_.FillXArray();
                    Scene2(stage_, model_);
                }else{
                    Text blad = new Text("Błędne dane");
                    blad.setTranslateY(120);
                    root.getChildren().add(blad);
                }*/
                database_.SetUser(user.getCharacters().toString());
                database_.SetPass(pass.getCharacters().toString());
                Scene2(stage_, database_);
            }
        });

        Scene scene = new Scene(root, 500, 500);
        root.getChildren().addAll(submit, user, pass, info);

        stage_.setTitle("Ksiunszki");
        stage_.setScene(scene);

        stage_.show();
    }

    public void Scene2(Stage stage_, DB database_){

        StackPane stackPane2 = new StackPane();
        Scene scene2 = new Scene(stackPane2, 500, 500);

        Button dodaj_ksiazke = new Button();
        dodaj_ksiazke.setText("Dodaj książkę");
        dodaj_ksiazke.setTranslateY(70);
        dodaj_ksiazke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DodajKsiazke(stage_, database_);
            }
        });


        Button wyszukaj_isbn = new Button();
        wyszukaj_isbn.setText("Wyszukaj po numerze isbn");
        wyszukaj_isbn.setTranslateY(30);
        wyszukaj_isbn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WyszukajISBN(stage_, database_);
            }
        });


        Button wyszukaj_autor = new Button();
        wyszukaj_autor.setText("Wyszukaj po autorze");
        wyszukaj_autor.setTranslateY(-10);
        wyszukaj_autor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WyszukajAutor(stage_, database_);
            }
        });


        Button wyswietl_wszystkich = new Button();
        wyswietl_wszystkich.setText("Wyświetl wszystkie pozycje");
        wyswietl_wszystkich.setTranslateY(-50);
        wyswietl_wszystkich.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WyswietlWszystkich(stage_, database_);
            }
        });


        stackPane2.getChildren().addAll(dodaj_ksiazke, wyszukaj_isbn, wyszukaj_autor, wyswietl_wszystkich);

        stage_.setTitle("Ksiunszki");
        stage_.setScene(scene2);

        stage_.show();
    }


    public void DodajKsiazke(Stage stage_, DB database_){
        StackPane stackPaneISBN = new StackPane();
        Scene sceneISBN = new Scene(stackPaneISBN, 500, 500);


        TextField wprowadz_isbn = new TextField("Wprowadź ISBN");
        wprowadz_isbn.setTranslateY(-70);
        wprowadz_isbn.setMaxWidth(200);

        TextField wprowadz_tytul = new TextField("Wprowadź tytuł");
        wprowadz_tytul.setTranslateY(-30);
        wprowadz_tytul.setMaxWidth(200);

        TextField wprowadz_autor = new TextField("Wprowadź autora");
        wprowadz_autor.setTranslateY(10);
        wprowadz_autor.setMaxWidth(200);


        TextField wprowadz_rok = new TextField("Wprowadź rok");
        wprowadz_rok.setTranslateY(50);
        wprowadz_rok.setMaxWidth(200);

        String isbn1 = wprowadz_isbn.getCharacters().toString();
        String autor1 = wprowadz_autor.getCharacters().toString();
        String rok1 = wprowadz_rok.getCharacters().toString();
        String tytul1 = wprowadz_tytul.getCharacters().toString();

        Button sumbit = new Button();
        sumbit.setText("Dodaj");
        sumbit.setTranslateY(90);
        sumbit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String isbn1 = wprowadz_isbn.getCharacters().toString();
                String autor1 = wprowadz_autor.getCharacters().toString();
                String rok1 = wprowadz_rok.getCharacters().toString();
                String tytul1 = wprowadz_tytul.getCharacters().toString();

                database_.addBook(isbn1, tytul1, autor1, rok1);
                StackPane dodane_pane = new StackPane();
                Scene dodane = new Scene(dodane_pane, 500, 500);
                Text text = new Text ("DODANE");
                dodane_pane.getChildren().add(text);

                Button submit = new Button();
                submit.setText("Wróć");
                submit.setTranslateY(200);
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Scene2(stage_, database_);
                    }
                });

                dodane_pane.getChildren().add(submit);


                stage_.setScene(dodane);

            }
        });


        stackPaneISBN.getChildren().addAll(sumbit, wprowadz_isbn, wprowadz_autor, wprowadz_rok, wprowadz_tytul);

        stage_.setTitle("Ksiunszki");
        stage_.setScene(sceneISBN);
        stage_.show();

    }

    public void WyswietlISBN(Stage stage_, DB database_, String ISBN_){


        database_.byISBN(ISBN_);
        StackPane Wyswietl_pane = new StackPane();
        Scene sceneWyswietl= new Scene(Wyswietl_pane, 500, 700);
        //Wyswietl_pane.getChildren().addAll();

        Text naglowek = new Text("Znalezione ISBN");
        naglowek.setTranslateY(-300);
        Wyswietl_pane.getChildren().add(naglowek);

        for(int i=0; i<database_.rekordy.size(); i++){
            String jeden_rekord="";
            for(int j=0; j<database_.rekordy.get(i).dane.size(); j++){
                jeden_rekord=jeden_rekord+" "+database_.rekordy.get(i).dane.get(j);
            }
            Text jeden_rekord_text = new Text(jeden_rekord);
            jeden_rekord_text.setTranslateY(-270+i*15);
            Wyswietl_pane.getChildren().add(jeden_rekord_text);
        }

        database_.rekordy=null;

        Button submit = new Button();
        submit.setText("Wróć");
        submit.setTranslateY(200);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene2(stage_, database_);
            }
        });

        Wyswietl_pane.getChildren().add(submit);



        stage_.setTitle("Ksiunszki");
        stage_.setScene(sceneWyswietl);
        stage_.show();

    }

    public void WyszukajISBN(Stage stage_, DB database_){

        StackPane stackPaneISBN = new StackPane();
        Scene sceneISBN = new Scene(stackPaneISBN, 500, 500);

        TextField wprowadz_isbn = new TextField("Wprowadź ISBN");
        wprowadz_isbn.setTranslateY(-20);
        wprowadz_isbn.setMaxWidth(200);

        Button sumbit_isbn = new Button();
        sumbit_isbn.setText("Wyszukaj");
        sumbit_isbn.setTranslateY(20);
        sumbit_isbn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WyswietlISBN(stage_, database_, wprowadz_isbn.getCharacters().toString());
            }
        });


        stackPaneISBN.getChildren().addAll(sumbit_isbn, wprowadz_isbn);

        stage_.setTitle("Ksiunszki");
        stage_.setScene(sceneISBN);
        stage_.show();

    }


    public void WyszukajAutor(Stage stage_, DB database_){

        StackPane stackPaneAuthor = new StackPane();
        Scene sceneAuthor = new Scene(stackPaneAuthor, 500, 500);

        TextField wprowadz_author = new TextField("Wprowadź nazwisko autora");
        wprowadz_author.setTranslateY(-20);
        wprowadz_author.setMaxWidth(200);

        Button sumbit_author = new Button();
        sumbit_author.setText("Wyszukaj");
        sumbit_author.setTranslateY(20);
        sumbit_author.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database_.byAuthor(wprowadz_author.getCharacters().toString());
            }
        });


        stackPaneAuthor.getChildren().addAll(sumbit_author, wprowadz_author);

        stage_.setTitle("Ksiunszki");
        stage_.setScene(sceneAuthor);
        stage_.show();

    }

    public void WyswietlWszystkich(Stage stage_, DB database_){
        database_.getAll();

        StackPane Wyswietl_pane = new StackPane();
        Scene sceneWyswietl= new Scene(Wyswietl_pane, 500, 700);
        //Wyswietl_pane.getChildren().addAll();

        Text naglowek = new Text("Wszystkie pozycje");
        naglowek.setTranslateY(-300);
        Wyswietl_pane.getChildren().add(naglowek);

        for(int i=0; i<database_.rekordy.size(); i++){
            String jeden_rekord="";
            for(int j=0; j<database_.rekordy.get(i).dane.size(); j++){
                jeden_rekord=jeden_rekord+" "+database_.rekordy.get(i).dane.get(j);
            }
            Text jeden_rekord_text = new Text(jeden_rekord);
            jeden_rekord_text.setTranslateY(-270+i*15);
            Wyswietl_pane.getChildren().add(jeden_rekord_text);
        }

        database_.rekordy=null;

        Button submit = new Button();
        submit.setText("Wróć");
        submit.setTranslateY(200);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene2(stage_, database_);
            }
        });

        Wyswietl_pane.getChildren().add(submit);


        stage_.setTitle("Ksiunszki");
        stage_.setScene(sceneWyswietl);
        stage_.show();

    }


}





