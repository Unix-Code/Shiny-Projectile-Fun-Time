package backend;

import Graphics.Sprite;
import java.awt.image.BufferedImage;

/**
 *
 * @author David
 */
public enum ProjectileType {
    Strike(0), Blast(1), Bolt(2);
    
    private int sizeX;
    private int sizeY;
    
    private BufferedImage[] images;
    
    ProjectileType(int type) {
        switch(type) {
            case 0 :
                sizeX = 41;
                sizeY = 17;
                images = Sprite.interpretSpriteSheet(sizeX, sizeX, 4, "projectiles/Projectile Sprite Sheet - Strike");
                break;
            case 1 :
                sizeX = 15; 
                sizeY = 12;
                images = Sprite.interpretSpriteSheet(sizeX, sizeY, 4, "projectiles/Projectile Sprite Sheet - Blast");
                break;
            case 2 :
                sizeX = 9;
                sizeY = 35;
                images = Sprite.interpretSpriteSheet(sizeX, sizeY, 4, "projectiles/Projectile Sprite Sheet - Bolt");
                break;
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public BufferedImage[] getImages() {
        return images;
    }
}
