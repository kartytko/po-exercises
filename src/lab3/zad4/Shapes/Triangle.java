package lab3.zad4.Shapes;

public class Triangle extends Shape{

    public Triangle (double wymiar_){
        super(wymiar_);
    }

    @Override
    public void draw() {
        System.out.println( "\n"+"*\n" +
                "**\n" +
                "* *\n" +
                "*  *\n" +
                "*****");
    }
}
