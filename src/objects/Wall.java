package objects;

import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Wall extends StaticObject {
    
    public Wall(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "wall";
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override 
    public boolean isTransposable() {
        return false;
    }

    @Override 
    public void destroy() {
        Room r = GameEngine.getInstance().getCurrentRoom();
        r.removeObject(this);
        r.addToGame(new CrackedWall(getPosition()));
    }
    
}
