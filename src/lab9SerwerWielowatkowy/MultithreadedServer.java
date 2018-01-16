package lab9SerwerWielowatkowy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedServer {

    public static void main(String[] args) throws IOException {
        //short port = Short.parseShort(args[0]);
        short port = 6666;
        ServerSocket srv = null;
        try{
            srv = new ServerSocket(port);
            ExecutorService service = Executors.newFixedThreadPool(2);
            while(true) {
                Socket s = srv.accept();
                System.out.print("new connection accepted: ");
                System.out.println(s.getInetAddress());
                service.submit(new SockReader(s));
            }
        }catch(IOException e){
            System.out.println("Could not listen on port 6666");
            System.exit(-1);
        }
    }
}

class SockReader implements Runnable {
    private Scanner in;
    private PrintStream out;
    private Socket s;

    public static String given_ID="0123456789";
    public static String path="/home/kartytko/Pulpit/pliki_na_serwerze/";
    public static String password="przykopny";

    static List<String> logins = Arrays.asList("szymon", "ziomek");
    static List<String> passwords = Arrays.asList("przykopny", "ZoczYć/iikH");
    static List<String> ids = Arrays.asList("0123456789", "9876543210");
    static String numer;


    public SockReader(Socket s) throws IOException {
        this(s.getInputStream(),s.getOutputStream());
        this.s = s;
    }

    public SockReader(InputStream input,OutputStream output) {
        in = new Scanner(input);
        out = new PrintStream(output);
    }

    private void msg(String msg) {
        System.out.print("SRV: ");
        System.out.println(msg);
    }
    public void run() {
        msg("serving new connection");

        String inputLine;
        CheckIfLoginAnswer check_if_login_answer;
        while( (!Thread.currentThread().isInterrupted()) && in.hasNextLine() ) {
            /*String line = in.nextLine();
            if(line.equals("LOGOUT")){
                break;
            }
            msg(line);
            out.println(line);
*/
            inputLine = in.nextLine();
            check_if_login_answer= CheckIfLogin(inputLine);

            if(check_if_login_answer.is_login_command) {
                if(!check_if_login_answer.correct_username){
                    out.println("Nieistniejący login");
                    continue;
                }

                if(CheckPassword(check_if_login_answer)){
                    out.println("Zalogowano. Twoje ID to "+ids.get(check_if_login_answer.index));
                }else{
                    out.println("Levenshtein: " + levenshteinDistance(check_if_login_answer.pass, passwords.get(check_if_login_answer.index)));
                }
                continue;
            }

            if(CheckIfLogOut(inputLine)){
                if(CheckID(inputLine.substring(7,inputLine.length()))){
                    out.println("Logged out");
                    break;
                }else{
                    out.println("Niepoprawne ID");
                    continue;
                }

            }
            if(CheckIfLs(inputLine)){
                System.out.println(inputLine.substring(3, inputLine.length()));
                if(CheckID(inputLine.substring(3, inputLine.length()))){
                    String message = "pliki: "+ ReadFiles();
                    out.println(message);
                }else{
                    out.println("Niepoprawne ID");
                }

                continue;
            }
            if(CheckIfGet(inputLine)){
                if(inputLine.length()<14){
                    out.println("Zbyt krótkie zapytanie zapytanie");
                    continue;
                }else{
                    if(CheckID(inputLine.substring(4, 14))){
                        if(CheckIfFileExist(inputLine.substring(15, inputLine.length()))){
                            try{out.println("Plik znaleziony. Zawartość to: "+ GetFile(inputLine.substring(15, inputLine.length())));
                            }catch(IOException e){
                                e.printStackTrace();
                            }
                            continue;
                        }else{
                            out.println("Plik nie istnieje");
                            continue;
                        }

                    }else{
                        out.println("Niepoprawne ID");
                        continue;
                    }
                }
            }

            if(inputLine.equals("find_pass")){
                out.println("Szukam...");
                continue;
            }

            out.println("Niepoprawna komenda");

            //Scanner myScaner = new Scanner(System.in);
            //String wiadomosc = myScaner.nextLine();
            //   out.println("SIEMCIA");

        }
        try {
            out.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        msg("connection closed");
    }



    static class CheckIfLoginAnswer{
        public boolean is_login_command = false;
        public boolean correct_username = false;
        public String pass;
        public int index;
    }


    public static CheckIfLoginAnswer CheckIfLogin(String input) {
        String template_login = "LOGIN ";
        CheckIfLoginAnswer answer = new CheckIfLoginAnswer();

        if (input.length() >= 6 && template_login.equals(input.substring(0, 6))) {
            answer.is_login_command = true;


            int index_it = 0;
            int accurance_of_semicolon = input.indexOf(';');
            String input_login="";


            if(accurance_of_semicolon>=0){
                input_login = input.substring(6, accurance_of_semicolon);
                answer.pass=input.substring(accurance_of_semicolon+1, input.length());
            }


            for(String str_it : logins){
                if(str_it.equals(input_login)){
                    answer.correct_username=true;
                    answer.index = index_it;
                    break;
                }
                index_it++;
            }
        }

        return answer;
    }

    public static boolean CheckPassword(CheckIfLoginAnswer answer){
        //System.out.println(answer.pass);
        //System.out.println(answer.index);
        if(answer.pass.equals(passwords.get(answer.index))){
            return true;
        }
        return false;
    }

   /* public static boolean CheckPassword(String pass){
        if(pass.equals(password)){
            return true;
        }
        return false;
    }*/
    public static boolean CheckID(String id){
        System.out.println(id);
        for(int i=0; i<ids.size(); i++){
            if(id.equals(ids.get(i))){
                return true;
            }
        }
        return false;
    }



    public static boolean CheckIfLogOut(String input){
        if(input.length()>=7){
            if(input.substring(0,7).equals("LOGOUT ")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean CheckIfLs(String input){
        if(input.length()>=4){
            if(input.substring(0,3).equals("LS ")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static String ReadFiles(){
        //  System.out.println("readfiles");
        String files_list="";
        try{
            File dir = new File(path);
            for (final File fileEntry : dir.listFiles()) {
                String current_file = fileEntry.getName();
                files_list = files_list+current_file+" ";
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return files_list;
    }


    public static boolean CheckIfGet(String input){
        if(input.length()>=4){
            if(input.substring(0,4).equals("GET ")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }



    public static boolean CheckIfFileExist(String file_name){
        try{
            File dir = new File(path);
            for (final File fileEntry : dir.listFiles()) {
                String current_file = fileEntry.getName();
                if(file_name.equals(current_file)){
                    return true;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static String GetFile(String file_name) throws IOException{
        FileReader fileReader = new FileReader(path+"/"+file_name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        bufferedReader.close();
        return textLine;
    }



    //fragment z wikibooks
       public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }
}
