package kolokwium;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class WeWy {
    private static String sciezka_;

    public WeWy(String sciezka){
        sciezka_=sciezka;
    }

    public static Stack<Operator> pobierzDzialanie(){

        Stack <Operator> stos_ = new Stack <Operator>();
        try{

            File plik = new File(sciezka_);
            String odczyt;
            Scanner scan = new Scanner(plik);
            odczyt = scan.nextLine();

            for (int i = 0, n = odczyt.length(); i < n; i++) {
                if(odczyt.charAt(i)=='*') {
                    Character character = odczyt.charAt(i);
                    stos_.push(new Mnożenie(character.toString()));
                }else if(odczyt.charAt(i)=='/'){
                    Character character = odczyt.charAt(i);
                    stos_.push(new Dzielenie(character.toString()));
                }else if(odczyt.charAt(i)=='+'){
                    Character character = odczyt.charAt(i);
                    stos_.push(new Dodawanie(character.toString()));
                }else if('0' <= odczyt.charAt(i) && odczyt.charAt(i)<='9'){
                    Character character = odczyt.charAt(i);
                    stos_.push(new Stała(character.toString()));
                }else if (odczyt.charAt(i)=='!'){
                    Character character = odczyt.charAt(i);
                    stos_.push(new Silnia(character.toString()));
                }
            }


        }catch(Exception e){
            e.getStackTrace();
        }
        return stos_;
    }

    public static void zapiszWynik(Double v){}
}
