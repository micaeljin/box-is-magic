package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class StaticObject extends GameObject {
    
    public StaticObject(Point2D position) {
        super(position);
    }

    public abstract boolean isTransposable();
    // @Override 
    // public String getName() {
    //     return ""; 
    // }

    // @Override 
    // public Point2D getPosition() {
    //     return super.getPosition();
    // }

    // @Override 
    // public int getLayer() {
    //     return 0;
    // }

}
