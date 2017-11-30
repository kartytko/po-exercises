package lab6.zad1;

import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JPanel{
    public String name;
    public double wymiar;
    int x;
    int y;

    public  Shape(double wymiar_){
        this.wymiar = wymiar_;
    }

    public abstract void draw(Graphics arg0, int x, int y);


    public abstract void SetX(int x_);
    public abstract void SetY(int y_);
    public abstract int GetX();
    public abstract int GetY();
}