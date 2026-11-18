package objects;
import pt.iscte.poo.utils.Direction;

public abstract class PushableObject implements Pushable {

    

    @Override 
    public void push(Direction d) {

    }

    @Override 
    public boolean canPush(GameObject pusher) {
        return true;
    }
}
