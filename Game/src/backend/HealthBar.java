package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;

/**
 *
 * @author David
 */
public class HealthBar extends Obj{
    
    private int health;
    private final int fullHealth;
    private final Character owner;
    
    // For Disappearing Constant Health Bar
    private int lastHealth;
    private long thisTime;
    private long lastTime;
    
    private final int displayTime = 3000; // 3 seconds
    
    public HealthBar(int health, int x, int y, int w, int h, Character owner) {
        super(x, y - 15, w, h, ID.HealthMeter);
        this.health = health;
        this.fullHealth = health;
        this.owner = owner;
        
        lastTime = (new Date()).getTime();
    }
    
    public void tick() {
        x = owner.getX();
        y = owner.getY() - 15;
        lastHealth = health;
        // health -= (owner.getVelX() <= 0) ? 1 : -1; // Diagnostic
        health = Game.clamp(health, 0, 100);
        // System.out.println("Health: " + health); // Diagnostic for Health count
    }
    
    public void render(Graphics g) {
        // if (owner.getId() != ID.Player) {
            if (health == lastHealth) {
                thisTime = (new Date()).getTime();
            }
            else {
                lastTime = thisTime;
            }

            if (thisTime - lastTime >= displayTime) {
                return;
            }
       // }
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GRAY);
        g2d.drawRect(x, y, w, h);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, (int) (w * ((double)health/fullHealth)), h);
    }
    
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

