package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.game.Room;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends PushableObject implements Tickable{
    private boolean lit;
    private int ticks = 0;
    private final static int FUSE_TICKS = 10;

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

    @Override 
    public void tick() {
        if (!lit) return;
        ticks++;
        if (ticks >= FUSE_TICKS) {
            explode();
        }
    }

    public void explode() {
        Room r = GameEngine.getInstance().getCurrentRoom();
        List<Point2D> area = getPosition().getWideNeighbourhoodPoints();
        area.add(getPosition());

        for (Point2D p: area) {
            for (GameObject o: r.getObjectsAt(p)) {
                o.destroy();
            }
        }
    }
}
