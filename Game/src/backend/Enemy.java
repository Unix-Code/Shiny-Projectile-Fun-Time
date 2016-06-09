package backend;

import Graphics.Animation;
import Graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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

    private int XPValue;
    
    // For Disappearing
    private long startTime;
    private long thisTime;
    private boolean returning;
    private boolean starting = true;

    public final int fireSpeed = 3;
    private long shootTime;
    private long currTime;

    private int currHealth;
    private int prevHealth;

    private Animation animation;

    public Enemy(int x, int y, int w, int h, int health, int dmg, Handler handler) {
        super(x, y, w, h, 0, 0, health, ID.Enemy, handler);
        startX = x;
        startY = y;
        this.dmg = dmg;
        
        this.XPValue = 55;
        
        perimeter = new Rectangle(x - 500, y - 500, this.w + 1000, this.h + 1000);
        startTime = (new Date().getTime());
        thisTime = startTime + followTime * 1000 + 1;
        returning = false;

        currTime = (new Date().getTime());
        shootTime = currTime;

        currHealth = health;
        prevHealth = health;

        this.animation = new Animation(new BufferedImage[]{Sprite.getSprite(0, 0, "spooder"), Sprite.getSprite(1, 0, "spooder")}, 10);
        this.animation.start();
    }

    public void render(Graphics g) {
        // super.render(g);
        g.drawImage(animation.getSprite(), x - 2, y - 11, null);
        this.charHealth.render(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.draw(perimeter);
    }

    public void tick() {
        super.tick();
        animation.tick();
        currTime = (new Date().getTime());
        if (Math.abs(currTime - shootTime) > 1500 && startX != this.x && startY != this.y) {
            fire();
        }
        this.watchForPlayer();
    }

    public void fire() {
        double theta = 0;
        boolean secondTime = false;
        while (Math.abs(theta) <= Math.toRadians(180)) {
            // System.out.println(theta);
            ProjectileType[] projectiles = {ProjectileType.Blast/*, ProjectileType.Strike/*, ProjectileType.Bolt*/};

            // System.out.println(theta);
            double dx = 8.0 * Math.cos(theta);
            double dy = 8.0 * Math.sin(theta);
            // System.out.println("dx: " + dx + ", dy: " + dy + "\nX: " + tempObject.getX() + ", Y: " + tempObject.getY() + "\nMouseX: " + mouse.getX() + ", MouseY: " + mouse.getY() + "\nCamX: " + cam.getX() + ", CamY: " + cam.getY());
            double ratioX = (this.getW() / 2) / (Math.abs(dx));
            double ratioY = (this.getH() / 2) / (Math.abs(dy));

            double spawnX;
            double spawnY;

            if (ratioX < ratioY) {
                spawnX = dx * ratioX * 1.2 + this.getVelX();
                spawnY = dy * ratioX * 1.2 + this.getVelY();
            }
            else {
                spawnX = dx * ratioY * 1.2 + this.getVelX();
                spawnY = dy * ratioY * 1.2 + this.getVelY();
            }

            double adjustment = 1;

            ProjectileType randProjectile = projectiles[(int) (Math.random() * (projectiles.length))];

            BufferedImage projectileImg = randProjectile.getImages()[(int) (Math.random() * 4)];

            // rotate projectile image
            AffineTransform transform = new AffineTransform();
            transform.rotate(theta, projectileImg.getWidth() / 2, projectileImg.getHeight() / 2);
            AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
            projectileImg = op.filter(projectileImg, null);

            // if (dx/((Movable)tempObject).getVelX() > 0 && dy/((Movable)tempObject).getVelY() > 0) adjustment = 3;
            // System.out.println("Adjustment: " + adjustment);
            handler.addObject(new Projectile((int) (this.getX() + this.getW() / 2.0 + spawnX), (int) (this.getY() + this.getH() / 2.0 + spawnY), randProjectile.getSizeX(), randProjectile.getSizeY(), dx, dy, 0.3 * adjustment, 5, ID.EnemyProjectile, projectileImg, handler));
            shootTime = (new Date().getTime());

            if (Math.abs(theta - Math.toRadians(180)) < 0.0001 && !secondTime) {
                theta *= -1;
                secondTime = true;
            }

            theta += Math.toRadians(60);
        }
    }

    public int getDamage() {
        return dmg;
    }
    
    public int getXPValue() {
        return XPValue;
    }

    public void watchForPlayer() {
        perimeter = new Rectangle(x - 500, y - 500, this.w + 1000, this.h + 1000);

        boolean inPursuit = thisTime - startTime < (followTime * 1000);

        int speed = 1;

        Player player = handler.getPlayer();

        if (player.getBounds().intersects(perimeter)) {
            // Make Enemy Follow Player
            startTime = (new Date().getTime());
            starting = false;
        }

        if (inPursuit && !starting) {
            double diffX = this.x - player.getX() - player.getW() / 2, diffY = this.y - player.getY() - player.getH() / 2;
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
