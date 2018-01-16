package lab7.zad4;

import java.util.LinkedList;

public class Kadry {
    private LinkedList<Pracownik> pracownicy_;

    //jeśli flaga==0 to Student, w przeciwnym wypadku PracownikEtatowy
    public void DodajPracownika(String pesel_, double wynagrodzenieBrutto_, boolean flaga_){
        if(flaga_){
            Student pracownik = new Student(pesel_, wynagrodzenieBrutto_);
            pracownicy_.add(pracownik);
        }else{
            PracownikEtatowy pracownik = new PracownikEtatowy(pesel_, wynagrodzenieBrutto_);
            pracownicy_.add(pracownik);
        }
    }

    public boolean UsunPracownika(String pesel){
        for(int i=0; i<pracownicy_.size(); i++){
            if(pracownicy_.get(i).pesel_.equals(pesel)){
                pracownicy_.remove(i);
                return true;
            }
        }
        return false;
    }

    public void SetWynagrodzenieBrutto(String pesel, double noweWynagordzenieBrutto){
        for(int i=0; i<pracownicy_.size(); i++){
            if(pracownicy_.get(i).pesel_.equals(pesel)){
                pracownicy_.get(i).wynagrodzenieBrutto_ = noweWynagordzenieBrutto;
            }
        }
    }

    public double GetWynagrodzenieBrutto(String pesel){
        for(int i=0; i<pracownicy_.size(); i++){
            if(pracownicy_.get(i).pesel_.equals(pesel)){
                return pracownicy_.get(i).wynagrodzenieBrutto_;
            }
        }
        return 0;
    }

    public double GetWynagrodzenieNetto(String pesel){
        for(int i=0; i<pracownicy_.size(); i++){
            if(pracownicy_.get(i).pesel_.equals(pesel)){
                return pracownicy_.get(i).wynagrodzenieNetto();
            }
        }
        return 0;
    }

}
