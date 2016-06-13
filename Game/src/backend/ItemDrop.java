package backend;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class ItemDrop extends Obj {
    private BufferedImage bag;
    private int tier;
    private final ItemType[] itemTypes;
    private Item droppedItem;
    private Handler handler;

    public ItemDrop(int dropX, int dropY, int tier, Handler handler) {
        super(dropX, dropY, 48, 48, ID.Item);
        this.tier = tier;
        this.handler = handler;
        this.itemTypes = new ItemType[]{ItemType.Chest, ItemType.Helmet, ItemType.Boots, ItemType.Weapon, ItemType.Shield};
        droppedItem = new Item(this.x, this.y, itemTypes[(int)(itemTypes.length * Math.random())], this.tier, handler);
        
        String sType;
        switch(this.tier) {
            case 1 :
                sType = "Brown";
                break;
            case 2 :
                sType = "Cyan";
                break;
            case 3 :
                sType = "Red";
                break;
            default :
                sType = "Brown";
                break;
        }
        try {
            bag = ImageIO.read(new File("res/items/" + sType + " Bag.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void tick() {
        if (handler.getPlayer().getBounds().intersects(this.getBounds())) {
            if (handler.getPlayer().pickUp(droppedItem)) handler.addItem(droppedItem);
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.drawImage(bag, x, y, null);
    }
    
    private Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
}
