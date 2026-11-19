package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;


public class Baba extends GameCharacter {

    public Baba(Point2D position, Direction direction) {
        super(position, direction);
    }

    @Override
    public String getName() {
        switch(getDirection()) {
            case UP: return "baba-u";
            case DOWN: return "baba-d";
            case LEFT: return "baba-l";
            default: return "baba-r";
        }
    }

    @Override
    public int getLayer() {
        return 3;
    }

}
