package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class WordObject extends PushableObject {
    public WordObject(Point2D p) {
        super(p);
    }

    @Override 
    public boolean canBePushedBy(GameObject pusher) {
        return !(pusher instanceof WordObject);
    }
}
