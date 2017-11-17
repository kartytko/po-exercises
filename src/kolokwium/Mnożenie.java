package kolokwium;

import java.util.Stack;

public class Mnożenie extends Operator2Arg{
    public double oblicz(Stack<Operator> s){return 1;}
    public Mnożenie(String wartosc){
        super(wartosc);
    }
}
