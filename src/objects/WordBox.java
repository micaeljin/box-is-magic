package objects;

import pt.iscte.poo.utils.Point2D;

public class WordBox extends WordObject {
    public WordBox(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "word-box";
    }

    @Override 
    public int getLayer() {
        return 2;
    }
}
