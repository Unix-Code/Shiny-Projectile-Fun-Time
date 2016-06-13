package backend;

import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Character extends Movable {
    
    protected HealthBar charHealth;
    
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
            if (this.getId() != ID.Player) {
                int rand = ((int)(Math.random() * 100) + 1);
                boolean drop = rand <= 45;
                System.out.println("Rand Drop: " + rand);
                if (drop) {
                    int randTier = 1;
                    if (rand >= 1 && rand <= 3) randTier = 3;
                    if (rand > 3 && rand <= 12) randTier = 2;
                    handler.addObject(new ItemDrop(x, y, randTier, handler));
                    
                }
                handler.addCorpse((Enemy) handler.removeObject(this));
            }
            else {
                handler.removeObject(this);
                ((Player)this).tilt();
            }
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
    
    private void detectProjectiles() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Projectile tempProjectile = null;
            
            if (handler.objects.get(i).getClass().getName().equals("backend.Projectile")) {
                tempProjectile = (Projectile) handler.objects.get(i);
                
                 if ((this.getId() == ID.Player && tempProjectile.getId() != ID.FriendlyProjectile) ||
                         (this.getId() == ID.Enemy && tempProjectile.getId() != ID.EnemyProjectile)) { 
                    if (this.getBounds().intersects(tempProjectile.getBounds())) {
                        if (this.getHealth() > 0) {
                            int damage = tempProjectile.getDamage() + ((this.getId() == ID.Player) ?  -1 * ((Player)this).getDefense() : 0);
                            this.setHealth(this.getHealth() - damage);
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
