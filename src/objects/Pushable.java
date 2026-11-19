package objects;
import pt.iscte.poo.utils.Direction;

public interface Pushable {
    boolean push(Direction d, GameObject pusher);

    boolean canBePushedBy(GameObject pusher);
}
