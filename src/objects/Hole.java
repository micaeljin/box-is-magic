package objects;

import pt.iscte.poo.utils.Point2D;

public class Hole extends StaticObject {

    private Point2D position;
    
    public Hole(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "hole";
    }

    // @Override
    // public Point2D getPosition() {
    //     return position;
    // }

    @Override
    public int getLayer() {
        return 5;
    }

    @Override 
    public boolean isTransposable() {
        return true;
    }
    
}
