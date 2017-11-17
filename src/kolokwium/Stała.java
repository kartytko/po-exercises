package kolokwium;

import java.util.Stack;

public class Stała extends Operator0Arg {
    public double oblicz(Stack<Operator> s ){return Double.parseDouble(this.getWartosc());}
    public Stała(String w){
        super(w);
    }
}
