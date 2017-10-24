package lab3.zad4.Shapes;


public class Circle extends Shape {

    public Circle (double wymiar_){
        super(wymiar_);
    }

    public void draw() {
        System.out.println ("   *    "
                + '\n' + " *   * "
                + '\n' + "*     *"
                + '\n' + " *   * "
                + '\n' + "   *   ");
    }

}
