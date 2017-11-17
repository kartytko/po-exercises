package kolokwium;

import java.util.Stack;

public class Dodawanie extends Operator2Arg {
    public double oblicz(Stack<Operator> s ){return 2;}
    public Dodawanie(String wartosc){
        super(wartosc);
    }
}
