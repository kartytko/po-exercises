package lab1.login;

import java.io.*;
public class LoginProgram {
    public static void main(String[] argv){
        Login log = new Login ("ala", "makota");
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            System.out.print("Prosze wpisac login");
            String login = bfr.readLine();
            System.out.print("Prosze wpisac haslo");
            String haslo = bfr.readLine();

            if(log.check(login, haslo)){
                System.out.println("OK");
            }else{
                System.out.println("Haslo lub login sa nieprawidlowe");
            }

        }catch(IOException e){e.printStackTrace();}

    }
}