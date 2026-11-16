package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends StaticObject {

    private Point2D position;
    
    public Wall(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "wall";
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
