package lab6.zad1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.red;

public class Circle extends Shape {

    public double wymiar_;
    public Circle (double wymiar_){
        super(wymiar_);
    }

    public void draw(Graphics arg0) {
        Graphics2D circle = (Graphics2D) arg0;
        Ellipse2D elipse = new Ellipse2D.Double(100, 100,100, 100);
        circle.draw(elipse);
        circle.setPaint(red);
        circle.fill(elipse);
    }

    @Override
    public void paint(Graphics arg0) {
        draw(arg0);
    }
}