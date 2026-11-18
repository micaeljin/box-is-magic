package objects;

import pt.iscte.poo.utils.Point2D;

public class Dirt extends StaticObject {

    public Dirt(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "dirt";
    }

    @Override
    public int getLayer() {
        return 0;
    }
}
