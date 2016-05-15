package backend;

import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Enemy extends Character {
    // For the patrolling Enemy
    private int startX;
    private int startY;
    private final int dmg;
    
    public Enemy(int x, int y, int w, int h, double velX, double velY, int health, int dmg, Handler handler) {
        super(x, y, w, h, velX, velY, health, ID.Enemy, handler);
        startX = x;
        startY = y;
        this.dmg = dmg;
    }
    
    public void render(Graphics g) {
        super.render(g);
    }
    
    public void tick() {
        super.tick();
        
        velX = Game.rebound(velX, x, startX - (w*3), startX + (w*3)); // Diagnostic (Back and Forth)
        velY = Game.rebound(velY, y, startY - (h*3), startY + (h*3)); // Diagnostic (Up and Down);
        // x = Game.clamp(x, startX - (w*3), startX + (w*3));
        // y = Game.clamp(y, startY - (h*3), startY + (h*3));
        
        if (Game.hitWall(x, 0, Game.width - w) || move.hitX) velX *= -1;
        if (Game.hitWall(y, 0, Game.height -h) || move.hitY) velY *= -1;
    }
    
    public int getDamage() {
        return dmg;
    }
}
