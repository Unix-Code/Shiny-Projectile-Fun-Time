package backend;

import java.awt.Graphics;

/**
 *
 * @author David
 */
public abstract class Obj {
    protected int x, y, w, h;
    protected final ID id;
    
    public Obj() {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        id = ID.StationaryObject;
    }
    
    public Obj(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        id = ID.StationaryObject;
    }
    
    //only to be used by Movable through super()
    public Obj(int x, int y, int w, int h, ID id) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.id = id;
    }

    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public ID getId() {
        return id;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
