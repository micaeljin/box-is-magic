package objects;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction;

public abstract class MovableObject extends GameObject {
    

    public MovableObject(Point2D position) {
        super(position);
    }


    // public abstract void move(Direction d);
    public void move(Direction d) {
        Point2D dest = getPosition().plus(d.asVector());
        setPosition(dest);
    }
}