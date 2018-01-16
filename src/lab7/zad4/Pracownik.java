package lab7.zad4;

public abstract class Pracownik {

    Pracownik(String pesel, double wynagrodzenieBrutto){
        pesel_=pesel;
        wynagrodzenieBrutto_=wynagrodzenieBrutto;
    }

    public String pesel_;
    public double wynagrodzenieBrutto_;
    abstract public double wynagrodzenieNetto();
}
