package backend;

import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Character extends Movable {
    
    protected HealthBar charHealth;
    private int XPValue;
    
    public Character() {
        super();
        charHealth = new HealthBar(100, x, y, w, 10, this);
    }
    
    public Character(int x, int y, int w, int h, double velX, double velY, int health, Handler handler) {
        super(x, y, w, h, velX, velY, ID.Character, handler);
        charHealth = new HealthBar(health, x, y, w, 10, this);
    }

    // only to be used by Player or Enemy through super()
    public Character(int x, int y, int w, int h, double velX, double velY, int health,  ID id, Handler handler) {
        super(x, y, w, h, velX, velY, id, handler);
        charHealth = new HealthBar(health, x, y, w, 10, this);
        XPValue = 10; // Diagnostic
    }

    public void render(Graphics g) {
        super.render(g);
        charHealth.render(g);
    }

    public void tick() {
        super.tick();
        
        charHealth.tick();
        this.detectProjectiles();
        if (!(this.getHealth() > 0)) {
            handler.addCorpse((Character) handler.removeObject(this));
        }
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
    
    public int getXPValue() {
        return XPValue;
    }
    
    private void detectProjectiles() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Projectile tempProjectile = null;
            
            if (handler.objects.get(i).getClass().getName().equals("backend.Projectile")) {
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
