package lab1.liczbypierwsze;

import java.io.*;
import java.util.Scanner;

public class JIn {
    public static String getString() {
        String text = null;
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);

            text = bfr.readLine();
        }catch(IOException e){e.printStackTrace();}
        return text;
    }

    public static int getInt(){
        int number = 0;
        Scanner rd = new Scanner(System.in);
        number	= rd.nextInt();

        return number;
    }

}