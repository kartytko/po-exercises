package lab6.zad1;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JPanel{
    public String name;
    public double wymiar;
    public  Shape(double wymiar_){
        this.wymiar = wymiar_;
    }

    public abstract void draw(Graphics arg0);

}