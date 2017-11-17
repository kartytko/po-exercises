package kolokwium;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String [] args){
        Stack<Operator> stos = new Stack<>();

        System.out.print("Podaj ścieżkę do pliku");
        Scanner scanner = new Scanner(System.in);
        String odczyt = scanner.nextLine();
        WeWy wewy = new WeWy (odczyt);
        stos = wewy.pobierzDzialanie();

        stos.pop().oblicz(stos);
    }
}
