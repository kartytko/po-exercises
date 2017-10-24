package lab1.liczbypierwsze;

public class LiczbyPierwsze{

    private int limit_;

    public LiczbyPierwsze(int limit){
        limit_ = limit	;
    }

    public void wypisz(){
        int licznik = 2;
        int licznik_wew = 2;
        boolean broken = false;
        while(licznik<=limit_){
            while(licznik_wew<licznik){
                if(licznik%licznik_wew == 0){
                    broken = true;
                    break;
                }
                licznik_wew++;
            }
            if(!broken){
                System.out.println(licznik + ", ");
            }
            broken = false;
            licznik_wew = 2;
            licznik++;
        }
    }

}