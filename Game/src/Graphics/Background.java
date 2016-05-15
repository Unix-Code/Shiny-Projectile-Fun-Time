package Graphics;

import backend.Game;
import backend.ID;
import backend.Obj;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Background extends Obj {
    
    BufferedImage[] images;

    public Background(int x, int y) {
        super(x, y, Game.width, Game.height, ID.Background);
         
        
        File dir = new File(new File("").getAbsolutePath() + "\\res\\Background Pictures");
        System.out.println(dir);
        System.out.println("Files found: " + dir.listFiles().length);
        
        this.images = new BufferedImage[dir.listFiles().length];
        
        for (int i = 0; i < dir.listFiles().length; i++) {      
            try {
                images[i] = ImageIO.read(dir.listFiles()[i]);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void tick() {
        if (Math.abs(x) > images.length * Game.width) {
            this.setX(Game.width);
        } 
        else {
            this.setX(x - 1); // Diagnostic
        }
        
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        for (int i = 0; i < images.length; i++) {
            BufferedImage image = images[i];
            
            g2d.drawImage(image, x + (i * Game.width), y, w, h, null);
        }
    }
    
}
