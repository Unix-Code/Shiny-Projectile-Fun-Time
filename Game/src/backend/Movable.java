package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public class Movable extends Obj {

    protected double velX, velY;
    protected Handler handler;
    protected Collision move;

    public Movable() {
        super();
        velX = 1.0;
        velY = 1.0;
        move = new Collision();
    }

    public Movable(int x, int y, int w, int h, double velX, double velY, Handler handler) {
        super(x, y, w, h, ID.MovingObject);
        this.velX = velX;
        this.velY = velY;
        this.handler = handler;
        move = new Collision();
    }

    //only to be used by Character through super()
    public Movable(int x, int y, int w, int h, double velX, double velY, ID id, Handler handler) {
        super(x, y, w, h, id);
        this.velX = velX;
        this.velY = velY;
        this.handler = handler;
        move = new Collision();
    }

    public void tick() {
        move = detectSurface();

        // Resets Jump
        /* if (move.hitY == true && velY >= 0 && this.getId() == ID.Player) {
            ((Player) this).falling = false;
            ((Player)this).jumping = false;
        } */
        
        x += this.velX; // move.moveX;
        y += this.velY; // move.moveY;

        // x = Game.clamp(x, 0, Game.width - w);
        // y = Game.clamp(y, 0, Game.height - h);
        // System.out.println("Velocity Y: " + velY);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    private Collision detectSurface() {
        // double[] move = {this.velX, this.velY};
        Collision move = new Collision(this.velX, this.velY);
        for (int i = 0; i < handler.objects.size(); i++) {
            Stationary tempStationary = null;

            if (handler.objects.get(i).getId() == ID.StationaryObject) {
                tempStationary = (Stationary) handler.objects.get(i);
            }
            if (tempStationary != null) {

                Rectangle nextCharX = new Rectangle((int) (this.x + this.velX), this.y, this.w, this.h);
                Rectangle nextCharY = new Rectangle(this.x, (int) (this.y + this.velY), this.w, this.h);

                if (nextCharX.intersects(tempStationary.getBounds())) {
                    Rectangle intersection = nextCharX.intersection(tempStationary.getBounds());

                    move.moveX = (this.velX > 0) ? this.velX - intersection.getWidth() : this.velX + intersection.getWidth();
                    move.hitX = true;
                }

                if (nextCharY.intersects(tempStationary.getBounds())) {
                    Rectangle intersection = nextCharY.intersection(tempStationary.getBounds());

                    move.moveY = (this.velY > 0) ? this.velY - intersection.getHeight() : this.velY + intersection.getHeight();
                    move.hitY = true;
                }
            }
        }

        return move;
    }
}
