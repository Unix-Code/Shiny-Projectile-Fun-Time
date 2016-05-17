package backend;

/**
 *
 * @author David
 */
public enum TileType {
    Grass(0, 255, 0), Stone(255, 0, 0), Water(0, 0, 255);
    
    private final int red, green, blue;
    
    TileType(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int[] getRGB() {
        return (new int[]{this.red, this.green, this.blue});
    }

    public Obj getTile(int x, int y, Handler handler) {
        return new Tile(x, y, handler);
    }
}
