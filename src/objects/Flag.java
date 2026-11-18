package objects;

import pt.iscte.poo.utils.Point2D;

public class Flag extends PushableObject implements Interactable{
    public Flag(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "flag";
    }

    @Override 
    public int getLayer() {
        return 2;
    }
}
