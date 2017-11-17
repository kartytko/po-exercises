package kolokwium;

public abstract class Operator implements Obliczanie{
    public Operator(String wartosc){
        wartosc_ = wartosc;
    }
    public Operator(){};
    String wartosc_;
    public String getWartosc(){
        return wartosc_;
    }
}
