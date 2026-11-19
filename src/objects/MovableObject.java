package objects;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

public abstract class MovableObject extends GameObject {
    

    public MovableObject(Point2D position) {
        super(position);
    }

    public boolean move(Direction d) {
        Point2D dest = getPosition().plus(d.asVector());
        Room r = GameEngine.getInstance().getCurrentRoom();

        for (GameObject o: r.getObjectsAt(dest)) {
            if (o instanceof Pushable && ((Pushable) o).canBePushedBy(this)) {
                if (!((Pushable) o).push(d, this)) {
                    return false;
                }
            } else if (!o.isTransposable()) {
                return false;
            }
        }
        
        setPosition(dest);
        return true;
    }
}