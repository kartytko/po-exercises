package lab1.liczbypierwsze;

public class Liczby{
    public static void main(String [] argv){
        int limit = 0;
        System.out.print("Podaj limit: ");
        limit = JIn.getInt();
        LiczbyPierwsze licz = new LiczbyPierwsze(limit);
        licz.wypisz();
    }
}