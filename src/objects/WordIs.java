package objects;

import pt.iscte.poo.utils.Point2D;

public class WordIs extends WordObject {
    public WordIs(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "word-is";
    }

    @Override 
    public int getLayer() {
        return 2;
    }
}
