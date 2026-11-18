package objects;

import pt.iscte.poo.utils.Point2D;

public class Box extends PushableObject implements Interactable{
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
}
