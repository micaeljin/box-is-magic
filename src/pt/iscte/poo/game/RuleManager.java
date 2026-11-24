package pt.iscte.poo.game;
import objects.GameObject;
import objects.Property;
import objects.PropertyWord;
import objects.WordBox;
import objects.WordIs;
import pt.iscte.poo.utils.Direction;

import java.util.Set;
import pt.iscte.poo.utils.Point2D;
import java.util.EnumSet;

public class RuleManager {
    private final Set<Property> active = EnumSet.noneOf(Property.class);

    public void update(Room r) {
        active.clear();

        for (GameObject o: r.getObjects()) {
            if (o instanceof WordBox) {
                check(r, o.getPosition(), Direction.RIGHT);
                check(r, o.getPosition(), Direction.DOWN);
            }
        }
    }

    public void check(Room r, Point2D start, Direction d) {
        Point2D isPos = start.plus(d.asVector());
        Point2D propPos = isPos.plus(d.asVector());

        boolean hasIs = false;
        for (GameObject o: r.getObjectsAt(isPos)) {
            if (o instanceof WordIs) {
                hasIs = true;
            }
        }

        if (!hasIs) return;

        for (GameObject o: r.getObjectsAt(propPos)) {
            if (o instanceof PropertyWord) {
                active.add(((PropertyWord) o).getProperty());
            }
        }
    }

    public boolean isActive(Property p) {
        return active.contains(p);
    }

    public boolean hasConflict() {
        return (isActive(Property.STOP) && isActive(Property.MOVE))
            || (isActive(Property.MOVE) && isActive(Property.WIN));
    }
}
