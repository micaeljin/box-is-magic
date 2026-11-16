package objects;

import pt.iscte.poo.utils.Point2D;

public class Grass extends StaticObject {

    private Point2D position;
    
    public Grass(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "grass";
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
