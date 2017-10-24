package lab3.zad1;


import java.util.ArrayList;
import java.util.LinkedList;

public class zad1 {

    public static void main(String [] args){
        LinkedList<Double> lista = new LinkedList<Double>();
        lista.add(1.5);
        lista.add(1.2);

        for(Double a : lista){
            System.out.println(a);
        }

        usunPierwszy(lista);
        for(Double a : lista){
            System.out.println(a);
        }

    }

    public double zmien(final double var){
        // var = var + 1;
        return var;
    }

    static public void usunPierwszy (final LinkedList<Double>list){
        list.remove(0);
    }

}
