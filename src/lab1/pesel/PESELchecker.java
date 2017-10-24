package lab1.pesel;

import java.io.*;

public class PESELchecker{
    public static void main(String [] argv){
        String pesel = null;
        System.out.print("Podaj pesel: ");
        try{
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            pesel = bfr.readLine();

        }catch(IOException e){e.printStackTrace();}

        if(pesel.length()!=11){
            System.out.println("Zla dlugosc numeru PESEL");
        }else{
            PESEL pes = new PESEL(pesel);
            if(pes.check()){
                System.out.println("Pesel poprawny");
            }else{
                System.out.println("Pesel niepoprawny");
            }
        }

    }
}