package lab3.zad4.Shapes;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class MainZad4 {
    public static void main(String [] args){
        Shape kolo = new Circle(5.0);
        Shape kolo2 = new Circle(15.0);
        Shape kwadrat = new Square(3.0);
        Shape trojkat = new Triangle(7.0);
        //  kolo.draw();
        //  kwadrat.draw();
        //  trojkat.draw();

        LinkedList<Shape> lista = new LinkedList<>();
        lista.add(kolo);
        lista.add(kolo2);
        lista.add(kwadrat);
        lista.add(trojkat);

        Collections.sort(lista, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                if(o1.wymiar>o2.wymiar){return 1;}
                if(o1.wymiar<o2.wymiar){return -1;}
                else return 0;
            }
        });

        for(Shape a : lista){
            a.draw();
            System.out.println(a.wymiar + "\n");
        }

    }
}
