package backend;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class UI extends Obj {
    // private ItemSlot[] items;
    private Handler handler;
    private BufferedImage image;
    
    public UI(Handler handler) {
        super(3*Game.width/5, 0, 156, Game.height, ID.UI);
        this.handler = handler;
        try {
            image = ImageIO.read(new File("res/user_interface/interface.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void tick() {
        this.setX(handler.getPlayer().getX() + Game.width/2);
        this.setY(handler.getPlayer().getY() - (Game.width/3 + 27) );
    }

    public void render(Graphics g) {
//        g.setColor(Color.GRAY);
//        g.fillRect(x, y, w, h);
        g.drawImage(image, x, y, null);
    }
    
}
