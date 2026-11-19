package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Direction;

public class Bomb extends PushableObject implements Tickable{
    private boolean lit;

    public Bomb(Point2D p) {
        super(p);
        lit = false;
    }

    @Override 
    public String getName() {
        return "bomb";
    }

    @Override 
    public int getLayer() {
        return 2;
    }

    @Override 
    public boolean push(Direction d, GameObject pusher) {
        boolean moved = super.push(d, pusher);
        if (moved && pusher instanceof GameCharacter) {
            lit = true;
        }
        return moved;
    }
}
