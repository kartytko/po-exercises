package lab6.zad1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.red;

public class Circle extends Shape {

    public Circle (double wymiar_){
        super(wymiar_);
        super.setName("circle");
    }

    public void draw(Graphics arg0, int x, int y) {
        Graphics2D circle = (Graphics2D) arg0;
        Ellipse2D elipse = new Ellipse2D.Double(x, y,super.wymiar, super.wymiar);
        circle.draw(elipse);
        circle.setPaint(new Color(250, 150,250));
        circle.fill(elipse);
    }

    public void SetX(int x_){this.x = x_;}
    public void SetY(int y_){this.y = y_;}
    public int GetX(){return this.x;}
    public int GetY(){return this.y;}


    /*@Override
    public void paint(Graphics arg0) {
        draw(arg0, 1, 1);
    }*/
}