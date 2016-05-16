package backend;

/**
 *
 * @author David
 */
public class Collision {
    public double moveX;
    public double moveY;
    
    public boolean hitX;
    public boolean hitY;

    public Collision() {
        moveX = 0;
        moveY = 0;
        hitX = false;
        hitY = false;
    }
    
    public Collision(double moveX, double moveY) {
        this.moveX = moveX;
        this.moveY = moveY;
        hitX = false;
        hitY = false;
    }
}
