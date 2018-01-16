package lab7.zad4;

public class PracownikEtatowy extends Pracownik{

    PracownikEtatowy(String pes, double wynBru){
        super(pes, wynBru);
    }

    @Override
    public double wynagrodzenieNetto() {
        return super.wynagrodzenieBrutto_*0.6;
    }

}
