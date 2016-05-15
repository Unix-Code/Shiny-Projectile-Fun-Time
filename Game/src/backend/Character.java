package backend;

import Graphics.Animation;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Character extends Movable {
    
    protected HealthBar charHealth;
    
    protected boolean jumping;
    protected boolean falling;
    
    protected static final double maxSpeed = 12;
            
    protected static final float gravity = 0.8f;
    
    public Character() {
        super();
        charHealth = new HealthBar(100, x, y, w, 10, this);
    }
    
    public Character(int x, int y, int w, int h, double velX, double velY, int health, Handler handler) {
        super(x, y, w, h, velX, velY, ID.Character, handler);
        charHealth = new HealthBar(health, x, y, w, 10, this);
        falling = true;
        jumping = false;
    }

    // only to be used by Player or Enemy through super()
    public Character(int x, int y, int w, int h, double velX, double velY, int health,  ID id, Handler handler) {
        super(x, y, w, h, velX, velY, id, handler);
        charHealth = new HealthBar(health, x, y, w, 10, this);
        falling = true;
        jumping = false;
    }

    public void render(Graphics g) {
        super.render(g);
        charHealth.render(g);
    }

    public void tick() {
        
        /* if (id == ID.Enemy) {
            if (detectSurface(this.velX, 0)) this.velX *= -1; // reverse direction
            if (detectSurface(0, this.velY)) this.velY *= -1; // reverse direction
        }
        */
        
        if (falling || jumping) velY += gravity;
            
        if (velY > maxSpeed) {
            velY = maxSpeed;
        }
        
        super.tick();
        
        // if (move.hitY) falling = false;
        
        charHealth.tick();
        this.detectProjectiles();
        if (!(this.getHealth() > 0)) handler.removeObject(this);
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isFalling() {
        return falling;
    }
    
    public HealthBar getHealthBar() {
        return charHealth;
    }
    
    public int getHealth() {
        return charHealth.getHealth();
    }

    public void setHealth(int health) {
        charHealth.setHealth(health);
    }
    
    private void detectProjectiles() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Projectile tempProjectile = null;
            
            if (handler.objects.get(i).getClass().getName().equals("game.Projectile")) {
                tempProjectile = (Projectile) handler.objects.get(i);
                
                 if ((this.getId() == ID.Player && tempProjectile.getId() != ID.FriendlyProjectile) ||
                         (this.getId() == ID.Enemy && tempProjectile.getId() != ID.EnemyProjectile)) { 
                    if (this.getBounds().intersects(tempProjectile.getBounds())) {
                        if (this.getHealth() > 0) {
                            this.setHealth(this.getHealth() - tempProjectile.getDamage());
                        }
                        /*
                        else {
                            handler.removeObject(this);
                            System.out.println("POOF"); // Diagnostic
                        }
                        */
                        handler.removeObject(tempProjectile);
                        // System.out.println("Poof \nProjectile Removed"); // Diagnostic
                    }    
                }
            }
        }
    }
    
    
}
