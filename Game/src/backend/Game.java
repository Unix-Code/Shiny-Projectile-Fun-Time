package backend;

import Graphics.Camera;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * @author David
 */
public class Game extends Canvas implements Runnable {

    public static final int width = 640, height = 480;
    public static final int FPS = 60;

    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private Camera cam = new Camera(0, 0);
    // private BufferedImage level; 
    
    public Game() {
        handler = new Handler();        
        this.addKeyListener(new KeyCheck(handler));
        this.addMouseListener(new MouseCheck(handler));
        new Window(width, height, "Shiny Projectile Fun Time", this);
        
        // level = loadLevelImage( /* image */ );
        
        // handler.addObject(new Background(0, 0));
//        handler.addObject(new Stationary(0, 4 * height / 5, Game.width, 30, handler));
//        handler.addObject(new Stationary((int) (2.85 * width / 4), 353, 70, 30, handler));
//        handler.addObject(new Stationary(100, 50, 50, 20, handler));
//        handler.addObject(new Stationary(3 * width / 4 - 65, 290, 60, 40, handler));
        
//        for (int i = 0; i < Game.width; i += Tile.SIZE + 1) {
//            for (int j = 0; j < Game.height; j += Tile.SIZE + 1) {
//                handler.addObject(new Tile(i, j, handler));
//            }
//            
//        }
        this.loadLevelImage("level");
        handler.addObject(new Player(width / 2, height / 2, 32, 32, 100, handler));
        handler.addObject(new Enemy((3 * width) / 4 + 30, 300, 16, 48, 100, 1, handler));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        long nextRepaintDue = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
            
            if (nextRepaintDue > (new Date()).getTime()) {
                // too soon to repaint, wait...
                try {
                    Thread.currentThread().sleep(nextRepaintDue - (new Date()).getTime());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            nextRepaintDue = (new Date()).getTime() + 1000 / FPS;
            
        }

        stop();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void tick() {
        handler.tick();
        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getId() == ID.Player) {
                cam.tick((Player) handler.objects.get(i));
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
        
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        g2d.translate(cam.getX(), cam.getY()); // begin cam
        
        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY()); // end cam
        
        g.dispose();
        bs.show();
    }
    
    private void loadLevelImage(String file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int w = img.getWidth(), h = img.getHeight();
        
        for (int i = 0; i < w; i+=3) {
            for (int j = 0; j < h; j+=3) {
                int tilePixel = img.getRGB(i,j);
                int centerPixel = img.getRGB(i + 1, j + 1);
                int red = (tilePixel >> 16) & 0xff, green = (tilePixel >> 8) & 0xff, blue = (tilePixel) & 0xff;
                this.convertToTile(red, green, blue, i/(int)3, j/(int)3);
                
                // spawn new Tiles and Characters
            }
        }
    }
    
    private void convertToTile(int red, int green, int blue, int coordX, int coordY) {
        TileType[] tiles = {TileType.Grass, TileType.Stone, TileType.Water};
        int[] currRGB = {red, green, blue};
        
        for (TileType tile : tiles) {
            if (Arrays.equals(currRGB, tile.getRGB())) {
                handler.addObject(tile.getTile(coordX*(Tile.SIZE/* + 1*/), coordY*(Tile.SIZE/* + 1*/), handler));
            }
        }
    }
    
    public static int clamp(int var, int min, int max) {
        return (var >= max) ? var = max : ((var <= min) ? var = min : var);
    }

    public static double rebound(double var, int check, int min, int max) {
        return (check >= max || check <= min) ? var * -1.0 : var;
    }

    public static double rebound(double var, double check, double min, double max) {
        return (check >= max || check <= min) ? var * -1.0 : var;
    }
    
    public static boolean hitWall(int check, int min, int max) {
        return (check >= max || check <= min);
    }
    
    public static int round(double num) {
        return (int) ((num - Math.floor(num) >= 0.5) ? Math.floor(num) + 1 : Math.floor(num));
    }
}
