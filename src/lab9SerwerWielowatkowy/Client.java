package lab9SerwerWielowatkowy;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    //dla funkcji find_pass
    static boolean find_pass = false;
    static boolean checked_pass_length = false;
    static int pass_length = 255;
    static String find_pass_login = "";


    //przy znalezieniu dystansu <6
    static boolean closest_pass_found = false;
    static boolean levenshtein_first = false; //boolean pozwalający wywołać zmieniejszanie levenshteina poprzez zmienianie liter (brak usuwania/dodawania)
    static String closest_pass = "";
    static int closest_pass_length; //przechwouje długość pierwszego znalezionego hasła
    static String closest_pass_next = "";
    static int closest_pass_Levenshtein_value = 255;
    static int closest_pass_iterator;

    //do zmiany literek
    static public List<Character> char_list_tmp = new ArrayList<Character>();




    public static void main(String[] args) throws IOException {

        String file_with_passwords = "/home/kartytko/Pulpit/pliki_na_serwerze/polish-dic.txt/";
        FileReader fileReader = new FileReader(file_with_passwords);
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            //echoSocket = new Socket("192.168.43.233", 6666);
            echoSocket = new Socket("localhost", 6666);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;


        String textLine = bufferedReader.readLine();
        //System.out.println(textLine);
        System.out.println("Dostępne komendy:");
        System.out.println("LOGIN login;haslo");
        System.out.println("LS id");
        System.out.println("GET id nazwa_pliku");
        System.out.println("LOGOUT id");
        System.out.println("find_pass login");
        userInput = "";
        fillList();
        try {
            while (userInput!=null) {
                if(textLine==null){
                    find_pass=false;
                }

                //przy wprowadzeniu "find_pass login"
                if(userInput.length()>=10 && userInput.substring(0, 10).equals("find_pass ")){
                    find_pass_login=userInput.substring(10, userInput.length());
                    find_pass=true;
                    checked_pass_length = true;
                    userInput = "LOGIN "+find_pass_login+";";
                    out.println(userInput);
                    String current = in.readLine();
                    System.out.println("Serwer: " + current);
                    continue;
                }


                if(find_pass){
                    find_pass=true;
                    userInput = "LOGIN "+find_pass_login+";"+textLine;
                    System.out.println("userInput to "+userInput);
                    out.println(userInput);
                }else if(!closest_pass_found){
                    userInput = stdIn.readLine();
                    out.println(userInput);
                }


                if(closest_pass_found && levenshtein_first){
                    if(closest_pass_iterator+1==closest_pass_length){
                        levenshtein_first = false;
                        out.println(userInput);
                        String current = in.readLine();
                        System.out.println("Serwer: " + current);
                        continue;
                    }else{
                        boolean else_ = false;
                        if(char_list_tmp.size()!=0){
                            closest_pass_next = closest_pass.substring(0, closest_pass_iterator)+char_list_tmp.get(0)+closest_pass.substring(closest_pass_iterator+1, closest_pass.length());
                        }else{
                            closest_pass_iterator++;
                            closest_pass_next=closest_pass;
                            System.out.println("ELDO");
                            fillList();
                            else_ = true;
                            //continue; to continue jest zastąpione zmienną else_
                        }
                        userInput = "LOGIN "+find_pass_login+";"+closest_pass_next;
                        out.println(userInput);
                        String current = in.readLine();
                        System.out.println("Serwer: " + current);

                        if(current.length()>=10 && current.substring(0, 10).equals("Zalogowano")){
                            closest_pass_found=false;
                            levenshtein_first=false;
                            continue;
                        }
                        int serwer_levenshtein_output = Integer.parseInt(current.substring(13, current.length()));
                        if(serwer_levenshtein_output>closest_pass_Levenshtein_value){
                            //System.out.println(closest_pass_iterator + " " +char_list.get(0)+" "+has_it+" levenstein wiekszy");
                            closest_pass_next=closest_pass;
                            char_list_tmp.remove(0);
                            continue;
                        }else if(serwer_levenshtein_output<closest_pass_Levenshtein_value){
                            closest_pass = closest_pass_next;
                            closest_pass_iterator++;
                            fillList();
                            closest_pass_Levenshtein_value=serwer_levenshtein_output;
                        }else{
                            char_list_tmp.remove(0);
                        }

                        continue;
                    }



                }

                //odbieranie odpowiedzi serwera
                String current = in.readLine();
                System.out.println("Serwer: " + current);


                if(current.length()>=10 && current.substring(0, 10).equals("Zalogowano")){
                    find_pass=false;
                }

                //znajdowanie długości poszukiwanego hasła
                if(checked_pass_length  && current.length()>=13 && current.substring(0, 13).equals("Levenshtein: ")){
                    checked_pass_length=false;
                    pass_length = Integer.parseInt(current.substring(13, current.length()));
                }

                //przy wywołaniu funkcji find_pass sprawdzam, ile wynosi dystans Levenshteina
                if(current.length()>=13 && current.substring(0, 13).equals("Levenshtein: ") && find_pass){
                    if(Integer.parseInt(current.substring(13, current.length()))<6 && !closest_pass_found){
                        System.out.println("Bliskie hasło: "+textLine);
                        find_pass=false; //tu zamiast false'a dopisać funkcję, która zmienia litery w znalezionym haśle
                        closest_pass_found = true;
                        levenshtein_first = true;

                        closest_pass = textLine;
                        closest_pass_next = "pierwsza iteracja";
                        closest_pass_length = closest_pass.length();

                        closest_pass_Levenshtein_value = Integer.parseInt(current.substring(13, current.length()));
                        closest_pass_iterator=0;
                    }
                }

                if(current.equals("Logged out")){
                    break;
                }
                if(current.equals("Plik znaleziony.")){
                    //tutaj magia z pobieraniem pliku
                    break;
                }
                textLine = bufferedReader.readLine();
            }
        } finally{
            bufferedReader.close();
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();

    }


    //gunwo
    public static void minimalize(){

        if(!closest_pass_next.equals("pierwsza iteracja")){

        }else{
        }
    }


    static public void fillList(){
        //a-z
        for(char i=97; i<=122; i++){
            char_list_tmp.add(i);
        }
        //A-Z
        for(char i=65; i<=90; i++){
            char_list_tmp.add(i);
        }
        char_list_tmp.add((char)45); //-  45
        char_list_tmp.add((char)47); // /  47
        char_list_tmp.add((char)243); //ó  243
        char_list_tmp.add((char)261); //ą  261
        char_list_tmp.add((char)262); //Ć  262
        char_list_tmp.add((char)263); //ć  263
        char_list_tmp.add((char)281); //ę  281
        char_list_tmp.add((char)321); //Ł  321
        char_list_tmp.add((char)322); //ł  322
        char_list_tmp.add((char)324); //ń  324
        char_list_tmp.add((char)346); //Ś  346
        char_list_tmp.add((char)347); //ś  347
        char_list_tmp.add((char)378); //ź  378
        char_list_tmp.add((char)379); //Ż  379
        char_list_tmp.add((char)380); //ż  380

        char_list_tmp.add((char)261);

    }

}

