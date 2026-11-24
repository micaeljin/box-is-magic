package objects;

import pt.iscte.poo.utils.Point2D;

public class Hole extends StaticObject implements Interactable{

    private boolean covered = false;
    
    public Hole(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "hole";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override 
    public void interact(GameObject other) {
        if (!covered) {
            other.fallInto(this);
        }
    }

    public void cover() {
        covered = true;
    }
}
