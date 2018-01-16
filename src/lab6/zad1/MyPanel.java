package lab6.zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import static java.awt.event.MouseEvent.BUTTON1;

public class MyPanel extends java.awt.Panel implements MouseMotionListener, MouseListener{
    public LinkedList<Shape> array = new LinkedList<>();
    public Shape movingShape = null;

    MyPanel(){
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        movingShape = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x_pressed = e.getX();
        int y_pressed = e.getY();

        if(movingShape != null){
            movingShape.SetX(x_pressed);
            movingShape.SetY(y_pressed);
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x_pressed = e.getX();
        int y_pressed = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1){
            if (movingShape == null) {
                for(Shape shape_it : array){
                    if ( (shape_it.GetX() < x_pressed) && (x_pressed < shape_it.GetX()+shape_it.wymiar) && (shape_it.GetY() < y_pressed) && (y_pressed < shape_it.GetY()+shape_it.wymiar)){
                        movingShape = shape_it;
                      //  System.out.print("siema");
                        break;
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}



    public void paint(Graphics arg0){
        for(Shape shape : array){
            shape.draw(arg0, shape.GetX(), shape.GetY());
        }
    }

    public void add(Shape sh){
        array.add(sh);
    }


}
