package kolokwium;

import java.util.Stack;

public class Silnia extends Operator1Arg{
    @Override
    public double oblicz(Stack<Operator> s){
        return s.pop().oblicz(s);
    }

    public Silnia(String w){
        super(w);
    }

    private int fact(int f) {

        int wynik = 1;
        if (f == 0) {
            return 1;
        }else{
            while (f > 0){
                wynik *= f;
                f--;
            }

            return wynik;
        }
    }


}
