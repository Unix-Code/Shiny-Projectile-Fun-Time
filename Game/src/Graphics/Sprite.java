package Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Sprite {

    private static final int TILE_SIZE = 64;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("res/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid, String spritesheet) {
        
        return loadSprite(spritesheet).getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    public static BufferedImage[] interpretSpriteSheet(int width, int height, int numSprites, String spritesheet) {
        BufferedImage[] images = new BufferedImage[numSprites];
        
        for (int i = 0; i < images.length; i++) {
            images[i] = loadSprite(spritesheet).getSubimage(i * width, 0, width, height);
        }
        
        return images;
    }
}

