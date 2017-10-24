package lab2.zad3;

public class Punkt2D{
    protected double x_;
    protected double y_;

    Punkt2D(double x, double y){
        x_ = x;
        y_ = y;
    }

    public double getX(){
        return x_;
    }

    public double getY(){
        return y_;
    }

    public void setX(double new_x){
        x_ = new_x;
    }

    public void setY(double new_y){
        y_ = new_y;
    }

    public double distance(Punkt2D punkt){
        return Math.sqrt((punkt.x_ - this.x_)*(punkt.x_ - this.x_) + (punkt.y_ - this.y_)*(punkt.y_ - this.y_));
    }
}