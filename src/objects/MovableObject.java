package objects;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

public abstract class MovableObject extends GameObject {
    

    public MovableObject(Point2D position) {
        super(position);
    }

    public void move(Direction d) {
        Point2D dest = getPosition().plus(d.asVector());
        
        Room r = GameEngine.getInstance().getCurrentRoom();
        if(!r.canMove(dest)) {
            return;
        }
        
        setPosition(dest);
    }
}