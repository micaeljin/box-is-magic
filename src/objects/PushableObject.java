package objects;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class PushableObject extends MovableObject implements Pushable {

    PushableObject(Point2D p) {
        super(p);
    }

    @Override 
    public void push(Direction d) {

    }

    @Override 
    public boolean canPush(GameObject pusher) {
        return true;
    }
}
