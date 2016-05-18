package backend;


import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * 
 * @author David
 */
public class Tile extends Obj{
    
    protected BufferedImage texture;
    
    protected boolean playerOnTop;
    private Handler handler;
    public static final int SIZE = 64;
    
    public Tile(int x, int y, BufferedImage texture, Handler handler) {
        super(x, y, Tile.SIZE, Tile.SIZE, ID.Tile);
        this.texture = texture;
        playerOnTop = false;
        this.handler = handler;
    } 
    
    public void tick() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Player tempPlayer = null;
            
            if (handler.objects.get(i).getId() == ID.Player) {
                tempPlayer = (Player)handler.objects.get(i);
            }
            
            if (tempPlayer != null) {
                if (this.getBounds().intersects(tempPlayer.getBounds())) {
                    playerOnTop = true;
                }
                else {
                    playerOnTop = false;
                }
            }
        }
    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (!playerOnTop) {
            g2d.setColor(Color.RED);
        }
        else {
            g2d.setColor(Color.GREEN);
        }
        
        g2d.drawImage(texture, x, y, w, h, null);
        // g2d.draw(this.getBounds());
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, Tile.SIZE, Tile.SIZE);
    }
    
}