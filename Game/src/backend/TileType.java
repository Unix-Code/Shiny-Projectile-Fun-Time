package backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public enum TileType {
    Grass(0, 255, 0, 0), Stone(64, 224, 208, 1), Water(0, 0, 255, 2), Sand(241, 218, 12, 3), Dirt(121, 101, 40, 4), Missing(255, 0 , 234, 5), Ice(128, 128, 128, 6), Wood(182, 165, 76, 7), SandStone(128, 0, 128, 8);
    
    private final int red, green, blue;
    private BufferedImage texture;
    
    TileType(int red, int green, int blue, int type) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        
        texture = null;
        String file = "";
        
        // TO DO
        switch (type) {
            case 0:
                // Grass
                file = "grass";
                break;
            case 1:
                // Stone
                file = "stone";
                break;
            case 2:
                // Water
                file = "water";
                break;
            case 3:
                file = "sand";
                break;
            case 4:
                file = "dirt";
                break;
            case 5:
                file = "missing";
                break;
            case 6:
                file = "ice";
                break;
            case 7:
                file = "wood";
                break;
            case 8:
                file = "sandstone";
                break;
        }
        
        try {
            texture = ImageIO.read(new File("res/tile_textures/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getRGB() {
        return (new int[]{this.red, this.green, this.blue});
    }

    public Obj getTile(int x, int y, Handler handler) {
        
        return new Tile(x, y, texture, handler);
    }
}
