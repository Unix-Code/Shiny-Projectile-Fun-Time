package backend;

/**
 *
 * @author David
 */
public abstract class ValueBar extends Obj {
    private double currValue;
    private double maxValue;
    
    public ValueBar(double currValue, double maxValue) {
        super(3*(Game.width/5), 50, 100, 10, ID.UI);
        this.currValue = currValue;
        this.maxValue = maxValue;
    }

    public double getCurrValue() {
        return currValue;
    }

    public void setCurrValue(double currValue) {
        this.currValue = currValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
    
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
    
    abstract void calcValue();
    
}
