package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class GameCharacter extends MovableObject {
    private Point2D position;
    public GameCharacter(Point2D position) {
        super(position);
    }
}
