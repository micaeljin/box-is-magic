package objects;

import pt.iscte.poo.game.GameEngine;
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

    @Override 
    public void fallInto(Hole hole) {
        GameEngine.getInstance().requestRestart();
    }

    @Override 
    public void destroy() {
        GameEngine.getInstance().requestRestart();
    }

}
