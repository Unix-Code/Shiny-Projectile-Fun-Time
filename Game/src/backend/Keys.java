package backend;

/**
 *
 * @author David
 */
public enum Keys {
    W(0), S(1), A(2), D(3), Up(4), Down(5), Left(6), Right(7);
    
    protected int value;
    Keys(int value) {
        this.value = value;
    }
}
