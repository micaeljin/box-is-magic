package objects;
import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameObject implements ImageTile {
    private Point2D position;

    public GameObject(Point2D position) {
        this.position = position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override 
    public Point2D getPosition() {
        return position;
    }

    public boolean isTransposable() {
        return true;
    }

    public void fallInto(Hole hole) {
        GameEngine.getInstance().getCurrentRoom().removeObject(this);
    } 

}
