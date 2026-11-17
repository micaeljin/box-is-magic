package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;


public class Baba extends GameCharacter {
    private String direction = "r";

    public Baba(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "baba-" + direction;
    }

    @Override
    public int getLayer() {
        return 25;
    }
    
    public void move(Direction d) {
        super.move(d);
    }
}
