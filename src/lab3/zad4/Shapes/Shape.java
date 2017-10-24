package lab3.zad4.Shapes;

public abstract class Shape{
    public String name;
    public double wymiar;
    public  Shape(double wymiar_){
        this.wymiar = wymiar_;
    }

    public abstract void draw();

}