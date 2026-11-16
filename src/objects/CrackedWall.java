package objects;

import pt.iscte.poo.utils.Point2D;

public class CrackedWall extends StaticObject {

    private Point2D position;
    
    public CrackedWall(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "cracked-wall";
    }

    // @Override
    // public Point2D getPosition() {
    //     return position;
    // }

    @Override
    public int getLayer() {
        return 5;
    }
    
}
