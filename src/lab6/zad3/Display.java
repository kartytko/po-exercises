package lab6.zad3;

import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab5.zad4.NotAnImageException;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Display extends Application {

    //pola
    private Button btn = new Button();

    private ObservableList<String> to_open = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<String>(to_open);

    private Text info = new Text();
    private ObservableList<String> files_in_chosen_dir = FXCollections.observableArrayList();
    private String chosen_file ="";
    private Image chosen_photo;
    private String path_to_chosen_dir ="";

    private Map<String, Double> indico_results;
    private PieChart pieChart;



    //wybieranie folderu
    public void ButtonSettings(Stage primaryStage){
        btn.setText("Wybierz katalog");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                f.showSaveDialog(null);

                path_to_chosen_dir = f.getSelectedFile().toString();

                try{
                    reachFilesFromChosenDir();
                }catch(Exception e ){
                    e.getStackTrace();
                }

                Scene2(primaryStage);
            }
        });
    }


    //stworzenie listy wszystkich obrazów znajdujących się w wybranym folderze
    public void reachFilesFromChosenDir() throws Exception{
        String pattern = "[^\\.]*\\.(jpg|bmp|png|jpeg)";
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher;

        File dir = new File(path_to_chosen_dir+"/");

        for (final File fileEntry : dir.listFiles()) {
            String current_file = fileEntry.getName();
            matcher = patt.matcher(current_file);
            if(matcher.find()){
                files_in_chosen_dir.add(current_file);
            }else{
                System.out.println("Znaleziono pliki mające rozszerzenie inne niż jpg, bmp, png lub jpeg");
                throw new NotAnImageException();
            }
        }
    }


    //display listy rozwijalnej i komunikatu o znalezionych plikach
    public void Scene2(Stage primaryStage){

        if(files_in_chosen_dir.size()<1){
            info.setText("W wybranym folderze nie znajdują się żadne pliki graficzne");
        }else{
            info.setText("Po wybraniu pliku z listy, kliknij na niego dwukrotnie, by potwierdzić");
        }
        listViewSettings(primaryStage);

        StackPane stackPane2 = new StackPane();
        Scene scene2 = new Scene(stackPane2, 500, 500);

        stackPane2.getChildren().add(listView);
        stackPane2.getChildren().add(info);

        primaryStage.setScene(scene2);
        primaryStage.show();
    }


    //stworzenie listy rozwijalnej ze zbioru plików graficznych znajdujących się w wybranym folderze
    public void listViewSettings(Stage primaryStage){

        listView.setEditable(true);
        to_open.add("Kliknij dwukrotnie, by wybierać plik");
        listView.setCellFactory(ComboBoxListCell.forListView(files_in_chosen_dir));
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val,String new_val) -> {
            if(new_val!=null && !new_val.equals("Kliknij dwukrotnie, by wybierać plik")){
                chosen_file = new_val;
                Scene3(primaryStage);
            }
        });

    }



    public void Scene3(Stage primaryStage){

        //upload zdjęcia
        final ImageView selectedImage = new ImageView();
        String path_to_photo = path_to_chosen_dir +"/"+this.chosen_file +"/";

        uploadPhoto(path_to_photo);
        selectedImage.setImage(chosen_photo);
        selectedImage.setTranslateX(-200);


        //wykres indico
        indicoRecogniton();
        makePieChart();


        //dodanie do sceny
        StackPane final_pane = new StackPane();
        final_pane.getChildren().add(selectedImage);
        final_pane.getChildren().add(pieChart);

        Scene scene3 = new Scene(final_pane, 800, 500);
        primaryStage.setScene(scene3);
        primaryStage.show();
    }


    public void uploadPhoto (String path){
        try{
            chosen_photo = new Image(new FileInputStream(path));
        }catch(Exception e){
            e.getStackTrace();
        }
    }


    public void indicoRecogniton(){
        try{
            Indico indico = new Indico("48471054fd5f336df6dc0e9a9f843d7a");
            IndicoResult tmp = indico.imageRecognition.predict(new File(path_to_chosen_dir +"/"+this.chosen_file +"/"));
            indico_results = tmp.getImageRecognition();

        }catch(Exception e){
            e.getStackTrace();
        }
    }



    public void makePieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for(Map.Entry<String,Double> iterator : indico_results.entrySet()){
            String name = iterator.getKey();
            Double score = iterator.getValue();
            pieChartData.add(new PieChart.Data(name, score));
        }


        pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Wyniki Indico");
        pieChart.setMaxSize(250, 250);
        pieChart.setTranslateX(200);
        pieChart.setLegendVisible(true);
        pieChart.setLabelsVisible(true);


    }




    @Override
    public void start(Stage primaryStage) {

        ButtonSettings(primaryStage);

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Indico");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

