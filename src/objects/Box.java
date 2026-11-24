package objects;

import pt.iscte.poo.utils.Point2D;

public class Box extends PushableObject implements Interactable{

    private boolean inHole = false;

    public Box(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "box";
    }

    @Override 
    public int getLayer() {
        return 2;
    }   

    @Override 
    public boolean isTransposable() {
        return true;
    }

    @Override 
    public boolean canBePushedBy(GameObject pusher) {
        return !inHole;
    }

    @Override 
    public void fallInto(Hole hole) {
        hole.cover();
        inHole = true;
    }

    @Override 
    public void interact(GameObject other) {
        
    }
}
