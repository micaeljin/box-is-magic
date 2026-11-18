package objects;

import pt.iscte.poo.utils.Point2D;

public class WordMove extends PropertyWord{
    public WordMove(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "word-move";
    }

    @Override 
    public int getLayer() {
        return 2;
    }
}
