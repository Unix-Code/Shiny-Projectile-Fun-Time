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

    // Animation states
    private Animation walkLeft;
    private Animation walkRight;
    private Animation stand;
    private Animation jump;

    // Live animation
    private Animation animation;

    public Player(int x, int y, int w, int h, int health, Handler handler) {
        super(x, y, w, h, 0, 0, health, ID.Player, handler);
        walkLeft = new Animation(new BufferedImage[]{Sprite.getSprite(0, 1), Sprite.getSprite(2, 1)}, 12);
        walkRight = new Animation(new BufferedImage[]{Sprite.getSprite(0, 2), Sprite.getSprite(2, 2)}, 12);
        stand = new Animation(new BufferedImage[]{Sprite.getSprite(1, 0)}, 12);
        // to do
        jump = null;

        this.animation = stand;
        this.animation.start();
    }

    public void tick() {
        super.tick();
        this.detectCollision();
        animation.tick();
        System.out.println("Animation: " + ((animation.equals(walkLeft)) ? "Left" : ((animation.equals(walkRight)) ? "Right" : ((animation.equals(stand)) ? "Stand" : "IDK"))));
    }

    public void render(Graphics g) {
        g.drawImage(this.animation.getSprite(), this.x, this.y, null);
        
        this.charHealth.render(g);
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

    public Animation getStand() {
        return stand;
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
                        this.setHealth(this.getHealth() - tempEnemy.getDamage());
                    }
                    /*
                    else {
                        handler.removeObject(this);
                        System.out.println("POOF... THERE WE GO");
                    }
                     */
                }
            }
        }
    }
}
