package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class PropertyWord extends WordObject {
    public PropertyWord(Point2D p) {
        super(p);
    }

    public abstract Property getProperty();
}
