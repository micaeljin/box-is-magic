package objects;

import pt.iscte.poo.utils.Point2D;

public class WordStop extends PropertyWord{
    public WordStop(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "word-stop";
    }

    @Override 
    public int getLayer() {
        return 2;
    }

    @Override 
    public Property getProperty() {
        return Property.STOP;
    }
}
