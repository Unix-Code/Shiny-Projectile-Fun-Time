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
public class Item extends Obj {

    private ItemType type;
    private int tier;
    private BufferedImage image;
    private Handler handler;

    public Item(int x, int y, ItemType type, int tier, Handler handler) {
        super(x, y, 40, 40, ID.Item);
        this.type = type;
        this.tier = tier;
        this.handler = handler;
        System.out.println("Item: T" + tier + " " + type);
        try {
            image = ImageIO.read(new File("res/items/T" + tier + " " + type + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tick() {
        int adjustX = 0, adjustY = 0;
        switch (type) {
            case Helmet :
                adjustX = 59;
                adjustY = 223 + ((tier == 3) ? 2 : 0);
                break;
            case Chest :
                adjustX = 61 + ((tier == 3) ? 1 : 0) + ((tier == 2) ? 2 : 0);
                adjustY = 282 + ((tier == 3) ? 3 : 0) + ((tier == 2) ? 2 : 0);
                break;
            case Boots :
                adjustX = 60;
                adjustY = 335;
                break;
            case Weapon :
                adjustX = 6;
                adjustY = 279;
                break;
            case Shield :
                adjustX = 108;
                adjustY = 280;
                break;
        }
        this.x = handler.getPlayer().getX() + Game.width/2 + adjustX + 3;
        this.y = handler.getPlayer().getY() - Game.width/2 + adjustY + 76;
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public int getTier() {
        return tier;
    }

    public ItemType getType() {
        return type;
    }
}
