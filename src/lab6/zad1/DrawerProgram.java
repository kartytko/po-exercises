package lab6.zad1;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DrawerProgram extends JFrame{

    public static void main(String [] argv){

        MyPanel mp = new MyPanel();
        DrawerProgram dp = new DrawerProgram();

        dp.setSize(1000, 500);
        dp.setVisible(true);

        Circle c = new Circle(150);
        c.SetX(100);
        c.SetY(100);
        mp.add(c);

        Square s = new Square(150);
        s.SetX(200);
        s.SetY(200);
        mp.add(s);

        Triangle t = new Triangle(150);
        t.SetX(0);
        t.SetY(0);
        mp.add(t);

        dp.add(mp);
        dp.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

    }
}