package backend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Date;

/**
 *
 * @author David
 */
public class Enemy extends Character {
    // For the patrolling Enemy
    private int startX;
    private int startY;
    private final int dmg;
    
    private Rectangle perimeter;
    // in seconds
    private final int followTime = 3;
    
    // For Disappearing
    private long startTime;
    private long thisTime;
    private boolean returning;
    private boolean starting = true;
            
    public Enemy(int x, int y, int w, int h, int health, int dmg, Handler handler) {
        super(x, y, w, h, 0, 0, health, ID.Enemy, handler);
        startX = x;
        startY = y;
        this.dmg = dmg;
        perimeter = new Rectangle(x - 50, y - 50, this.w + 100, this.h + 100);
        startTime = (new Date().getTime());
        thisTime =  startTime + followTime*1000 + 1;
        returning = false;
    }
    
    public void render(Graphics g) {
        super.render(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.YELLOW);
        g2d.draw(perimeter);
    }
    
    public void tick() {
        super.tick();

        this.watchForPlayer();
    }
    
    public int getDamage() {
        return dmg;
    }
    
    public void watchForPlayer() {
        perimeter = new Rectangle(x - 50, y - 50, this.w + 100, this.h + 100);
        
        boolean inPursuit = thisTime - startTime < (followTime * 1000);
        
        int speed = 1;
        
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                Player player = (Player)handler.objects.get(i);
                
                if (player.getBounds().intersects(perimeter)) {
                   // Make Enemy Follow Player
                   startTime = (new Date().getTime());
                   starting = false;
                }
                
                if (inPursuit && !starting) {
                       double diffX = this.x - player.getX() - player.getW()/2, diffY = this.y - player.getY() - player.getH()/2;
//                    double distance = Math.sqrt((this.x - player.getX())*(this.x - player.getX()) + (this.y - player.getY())*(this.y - player.getY()));
//
//                    velX = (-1.0 / distance) * diffX;
//                    velY = (-1.0 / distance) * diffY;

                       double angle = Math.atan2(diffY, diffX);
                       
                       // To Do: Get rid of rounding and simply use double values
                       velX = -speed * Game.round(Math.cos(angle));
                       velY = -speed * Game.round(Math.sin(angle));
                }
                else if (!(player.getBounds().intersects(perimeter))) {
                    double diffX = this.x - startX, diffY = this.y - startY;
                    double angle = Math.atan2(diffY, diffX);
                    
                    
                    
                    // To Do: Get rid of rounding and simply use double values
                    velX = -Game.round(Math.cos(angle));
                    velY = -Game.round(Math.sin(angle));
                    returning = true;
                }
                
                if (this.x == startX && this.y == startY && returning) {
                    velX = 0;
                    velY = 0;
                    returning = false;
                } 
                thisTime = (new Date().getTime());
                // System.out.println("velX: " + velX + ", velY: " + velY + "\nIn Pursuit: " + inPursuit); // Diagnostic
            }
        }
    }
}
