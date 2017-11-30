package lab6.zad1;


import java.awt.*;

import static java.awt.Color.green;

public class Triangle extends Shape {

    public Triangle (double wymiar_){
        super(wymiar_);
        super.setName("triangle");
    }
    public void SetX(int x_){this.x = x_;}
    public void SetY(int y_){this.y = y_;}
    public int GetX(){return this.x;}
    public int GetY(){return this.y;}


    @Override
    public void draw(Graphics arg0, int new_x, int new_y) {
        int a = new_x;
        int b = new_x+(int)wymiar;
        int c = (int)wymiar + new_y;
        Graphics triangle = arg0;

        triangle.setColor(new Color(255, 200, 0));
        triangle.fillPolygon(new int[]{a, b, (a+b)/2}, new int[]{this.y, this.y, c}, 3);
        triangle.drawPolygon(new int[]{a, b, (a+b)/2}, new int[]{this.y, this.y, c}, 3);

    }
}

