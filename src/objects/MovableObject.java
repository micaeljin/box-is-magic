package objects;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

public abstract class MovableObject extends GameObject {
    

    public MovableObject(Point2D position) {
        super(position);
    }


    // public abstract void move(Direction d);
    public void move(Direction d) {
        Point2D dest = getPosition().plus(d.asVector());
        
        // if (!canMove(dest)) {
        //     return;
        // }
        
        
        setPosition(dest);
    }

    // private boolean canMove(Point2D dest) {
        // ImageTile object = Room.getObject(dest);
        
        // return object.isTransposable();
    // }

}