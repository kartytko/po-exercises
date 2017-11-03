package lab4.zad4;

import java.io.*;
import java.util.Scanner;

public class Cryptographer {
    /*statyczna metoda cryptfile, przyjmująca jako parametry plik do zaszyfrowania, plik w którym powinien zostać zapisany
    zaszyfrowany tekst oraz referencje do typuAlgorithm.

    statyczna metoda decryptfile działająca odwrotnie do cryptfile
    */
    public static void cryptfile(String path_from, String path_to, Algorithm type) throws IOException{

        FileReader fileReader = new FileReader(path_from);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(path_to);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine = bufferedReader.readLine();

            do {
                bufferedWriter.write(type.crypt(textLine+'\n'));

                textLine = bufferedReader.readLine();
                bufferedWriter. newLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }



    public static void decryptfile(String path_from, String path_to, Algorithm type) throws IOException{
        FileReader fileReader = new FileReader(path_from);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(path_to);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            String textLine = bufferedReader.readLine();

            do {
                bufferedWriter.write(type.decrypt(textLine+'\n'));

                textLine = bufferedReader.readLine();
                bufferedWriter.newLine();
            } while (textLine != null);
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
