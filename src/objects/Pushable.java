package objects;
import pt.iscte.poo.utils.Direction;

public interface Pushable {
    void push(Direction d);

    boolean canPush(GameObject pusher);
}
