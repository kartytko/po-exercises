package lab6.zad2;

import java.util.LinkedList;

public class Model {
    double a;
    double b;
    double c;
    double d;
    double e;
    int początek;
    int koniec;
    int czestotliwosc;
    int krok;
    LinkedList<Integer> x_array = new LinkedList<>();
    LinkedList<Integer> y_array = new LinkedList<>();

    public boolean PrzypiszWspolczynniki(CharSequence a_, CharSequence b_, CharSequence c_, CharSequence d_, CharSequence e_){
        if(isaNumber(a_) && isaNumber(b_) && isaNumber(c_) && isaNumber(d_) && isaNumber(e_)) {
            a = Double.parseDouble(a_.toString());
            b = Double.parseDouble(b_.toString());
            c = Double.parseDouble(c_.toString());
            d = Double.parseDouble(d_.toString());
            e = Double.parseDouble(e_.toString());
            return true;
        }else{
            return false;
        }
    }

    public boolean DodajPrzedziałiCzestotliwosc(CharSequence pocz, CharSequence kon, CharSequence cz){
        if(isaNumber(pocz) && isaNumber(kon) && isaNumber(cz)){
            początek = Integer.parseInt(pocz.toString());
            koniec = Integer.parseInt(kon.toString());
            czestotliwosc = Integer.parseInt(cz.toString());
            return true;
        }else{
            return false;
        }
    }

    public boolean isaNumber(CharSequence tmp){
        if(tmp.length()==0){
            return false;
        }
        int ilosc_kropek = 0;
        for(int i=0; i<tmp.length(); i++){
            char char_tmp = tmp.charAt(i);
            if(!(48<=char_tmp && char_tmp<=57)){
                if(char_tmp=='.'){
                    ilosc_kropek++;
                }else{
                    return false;
                }
                if(ilosc_kropek>1){
                    return false;
                }
            }
        }
        return true;
    }

    public void FillXArray(){
        int krok_ = (początek+koniec)/czestotliwosc;
        krok = krok_;
        for(int i=początek; i<koniec; i=i+krok){
            x_array.add(i);
        }
        x_array.add(koniec);
        FillYArray();
    }

    public void FillYArray(){
        for(int i=0; i<x_array.size(); i++){
            y_array.add(Oblicz(x_array.get(i)));
        }
    }

    public int Oblicz(int x){
        double wynik = a*(x*x*x*x)+b*(x*x*x)+c*(x*x)+d*(x)+e;
        return (int)wynik;
    }

}
