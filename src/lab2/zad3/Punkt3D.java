package lab2.zad3;

public class Punkt3D extends Punkt2D{
    protected double z_;
    public Punkt3D(double x, double y, double z){
        super(x, y);
        z_ = z;
    }

    public double getZ(){
        return z_;
    }

    public void setZ(double new_z){
        z_ = new_z;
    }

    public double distance(Punkt3D punkt){
        return Math.sqrt((punkt.x_ - this.x_)*(punkt.x_ - this.x_) + (punkt.y_ - this.y_)*(punkt.y_ - this.y_) + (punkt.z_ - this.z_)*(punkt.z_ - this.z_));
    }
}