package lab2.zad3;

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Test{

    public static void main(String [] agrv){
        LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
        int opcja = 0;
        boolean loop = true;
        while(loop){
            System.out.println('\n' + "1 - wczytaj punkt 3D" + '\n' + "2 - wyświetl wszystkie punkty" + '\n' + "3 - oblicz odległość" + '\n' + "4 - zakończ" + '\n' + "?");
            Scanner rd = new Scanner(System.in);
            opcja = rd.nextInt();
            switch (opcja) {
                case 1:
                    double x, y, z;

                    System.out.println("Podaj x: ");
                    x = rd.nextDouble();

                    System.out.println('\n' + "Podaj y: ");
                    y = rd.nextDouble();

                    System.out.println('\n' + "Podaj z: ");
                    z = rd.nextDouble();

                    Punkt3D nowy_punkt = new Punkt3D(x, y, z);
                    punkty.add(nowy_punkt);
                    break;


                case 2:
                    int licznik = 0;
                    for(Punkt3D e : punkty){
                        System.out.println("Punkt " + licznik + ": x = " + e.getX() + ", y = " + e.getY() + ", z = " + e.getZ());
                        licznik++;
                    }
                    break;


                case 3:
                    int punkt1 = 0;
                    int punkt2 = 0;
                    System.out.println("Podaj numery punktów, między którymi chcesz policzyć odległość. Punkt 1: ");
                    punkt1 = rd.nextInt();
                    System.out.println("Punkt 2: ");
                    punkt2 = rd.nextInt();
                    Punkt3D punkt_tmp = punkty.get(punkt1);
                    Punkt3D punkt_tmp2 = punkty.get(punkt2);
                    double odpowiedz = punkt_tmp.distance(punkt_tmp2);
                    System.out.println("Odległość to: " + odpowiedz);


                    break;

                case 4:
                    loop = false;
                    break;

                default:
                    System.out.println("Błędna opcja");
            }
        }
    }

}