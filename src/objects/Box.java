package objects;


import pt.iscte.poo.game.GameEngine;
import pt.iscte.poo.game.RuleManager;
import pt.iscte.poo.utils.Point2D;

public class Box extends PushableObject implements Interactable{

    private boolean inHole = false;

    public Box(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "box";
    }

    @Override 
    public int getLayer() {
        return 2;
    }

    private RuleManager rules() {
        return GameEngine.getInstance().getRules();
    }

    @Override 
    public boolean isTransposable() {
        return !rules().isActive(Property.STOP);
    }

    @Override 
    public boolean canBePushedBy(GameObject pusher) {
        return !inHole && rules().isActive(Property.MOVE);
    }

    @Override 
    public void fallInto(Hole hole) {
        hole.cover();
        inHole = true;
    }

    @Override 
    public void interact(GameObject other) {
        if (other instanceof GameCharacter && rules().isActive(Property.WIN)) {
            GameEngine.getInstance().requestNextLevel();
        }
    }
}
