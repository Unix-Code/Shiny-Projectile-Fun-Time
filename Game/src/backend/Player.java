package backend;

import Graphics.Animation;
import Graphics.Sprite;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author David
 */
public class Player extends Character {
    
    private boolean boosted;
    
    // Animation states
    private Animation walkLeft;
    private Animation walkRight;
    private Animation stand;
    private Animation walkBack;
    private Animation walkForward;

    // Live animation
    private Animation animation;
    
    private XPBar xpBar;
    
    private int dmg;
    private int speed;
    private int defense;

    public Player(int x, int y, int w, int h, int health, Handler handler) {
        super(x, y, w, h, 0, 0, health, ID.Player, handler);
        walkRight = new Animation(new BufferedImage[]{Sprite.getSprite(2, 1, "SpriteSheet- Wizard_Brown_Blue"), Sprite.getSprite(3, 1, "SpriteSheet- Wizard_Brown_Blue")}, 10);
        walkLeft = new Animation(new BufferedImage[]{Sprite.getSprite(0, 2, "SpriteSheet- Wizard_Brown_Blue"), Sprite.getSprite(1, 2, "SpriteSheet- Wizard_Brown_Blue")}, 10);
        stand = new Animation(new BufferedImage[]{Sprite.getSprite(0, 0, "SpriteSheet- Wizard_Brown_Blue")}, 10);
        walkBack = new Animation(new BufferedImage[]{Sprite.getSprite(0, 1, "SpriteSheet- Wizard_Brown_Blue"), Sprite.getSprite(1, 1, "SpriteSheet- Wizard_Brown_Blue")}, 10);
        walkForward = new Animation(new BufferedImage[]{Sprite.getSprite(1, 0, "SpriteSheet- Wizard_Brown_Blue"), Sprite.getSprite(2, 0, "SpriteSheet- Wizard_Brown_Blue")}, 10);
        
        this.animation = stand;
        this.animation.start();
        
        xpBar = new XPBar(0, 100, handler);
        
        dmg = 10;
        speed = 5;
        defense = 0;
        
        boosted = false;
    }

    public void tick() {
        super.tick();
        this.detectCollision();
        animation.tick();
        xpBar.tick();
        
        dmg = 10 + (xpBar.getLevel() - 1);
        speed = 5;
        defense = 0 + (xpBar.getLevel() - 1);
    }

    public void render(Graphics g) {
        // Graphics2D g2d = (Graphics2D)g;
        
        g.drawImage(this.animation.getSprite(), this.x, this.y, null);
        
        this.charHealth.render(g);
        this.xpBar.render(g);
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getWalkLeft() {
        return walkLeft;
    }

    public Animation getWalkRight() {
        return walkRight;
    }
    
    public Animation getWalkBack() {
        return walkBack;
    }
    
    public Animation getWalkForward() {
        return walkForward;
    }
    
    public Animation getStand() {
        return stand;
    }

    public boolean isNotBoosted() {
        return !boosted;
    }
    public void tilt() {
        boosted = true;
    }
    
    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    private void detectCollision() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Enemy tempEnemy = null;

            if (handler.objects.get(i).getId() == ID.Enemy) {
                tempEnemy = (Enemy) handler.objects.get(i);
            }

            if (tempEnemy != null) {
                if (this.getBounds().intersects(tempEnemy.getBounds())) {
                    if (this.getHealth() > 0) {
                        this.setHealth(this.getHealth() - (tempEnemy.getDamage() - this.defense));
                    }
                }
            }
        }
    }
}
