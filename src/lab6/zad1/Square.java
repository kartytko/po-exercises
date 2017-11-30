package lab6.zad1;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.blue;

public class Square extends Shape {

    public Square (double wymiar_){
        super(wymiar_);
        super.setName("square");
    }

    public void draw(Graphics arg0, int x, int y) {
        Graphics square = arg0;
        square.setColor(new Color(60, 200, 200));
        square.fillRect(x, y, (int)super.wymiar, (int)super.wymiar);
        square.drawRect(x, y, (int)super.wymiar, (int)super.wymiar);
    }



    public void SetX(int x_){this.x = x_;}
    public void SetY(int y_){this.y = y_;}
    public int GetX(){return this.x;}
    public int GetY(){return this.y;}

  /*  @Override
    public void paint(Graphics arg0) {
        draw(arg0, 1, 1);
    }*/
}
