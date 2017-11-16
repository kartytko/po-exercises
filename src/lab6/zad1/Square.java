package lab6.zad1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.blue;

public class Square extends Shape {

    public Square (double wymiar_){
        super(wymiar_);
    }

    public void draw(Graphics arg0) {

        Graphics g = arg0;
        g.setColor(blue);
        g.drawRect(50, 50, 10, 10);
    }

    @Override
    public void paint(Graphics arg0) {
        draw(arg0);
    }
}
