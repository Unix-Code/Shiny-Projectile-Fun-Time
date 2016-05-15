package backend;

import java.awt.Graphics;
import java.util.Date;

/**
 *
 * @author David
 */
public class Projectile extends Movable {
    private final int dmg;
    
    // in seconds
    private final double displayTime;
    
    // For Disappearing
    private long birthTime;
    private long thisTime;
            
    public Projectile(int x, int y, int w, int h, double velX, double velY, double displayTime, int dmg, ID id, Handler handler) {
        // ID can be ID.FriendlyProjectile, ID.EnemyProjectile, ID.NeutralProjectile
        super(x, y, w, h, velX, velY, id, handler);
        this.displayTime = displayTime;
        this.dmg = dmg;
        
        birthTime = (new Date().getTime());
        
    }

    public void tick() {
        super.tick();
        
        // if (Game.hitWall(x, 0, Game.width - w) || Game.hitWall(y, 0, Game.height - h) || move.hitX || move.hitY) handler.removeObject(this);
        
        thisTime = (new Date()).getTime();
        
        if (thisTime - birthTime >= (displayTime * 1000)) {
            // System.out.println("I've faded"); // Diagnostic
            handler.removeObject(this);
        }
        
    }

    public void render(Graphics g) {
        super.render(g);
        
    }
    
    public int getDamage() {
        return dmg;
    }   
}
