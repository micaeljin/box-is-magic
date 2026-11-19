package objects;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class PushableObject extends MovableObject implements Pushable {

    PushableObject(Point2D p) {
        super(p);
    }

    @Override 
    public boolean isTransposable() {
        return false;
    }

    @Override 
    public boolean push(Direction d, GameObject pusher) {
        return move(d);
    }

    @Override 
    public boolean canBePushedBy(GameObject pusher) {
        return true;
    }
}
