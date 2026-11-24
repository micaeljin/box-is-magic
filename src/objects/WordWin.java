package objects;

import pt.iscte.poo.utils.Point2D;

public class WordWin extends PropertyWord{
    public WordWin(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "word-win";
    }

    @Override 
    public int getLayer() {
        return 2;
    }

    @Override 
    public Property getProperty() {
        return Property.WIN;
    }
}
