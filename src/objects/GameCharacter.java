package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class GameCharacter extends MovableObject {
    private Direction direction;

    public GameCharacter(Point2D position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    @Override
    public boolean move(Direction d) {
        direction = d;
        return super.move(direction);
    }

    public static Direction directionFromLetter(String letter) {
        switch (letter.trim().toLowerCase()) {
            case "u": return Direction.UP;
            case "d": return Direction.DOWN;
            case "l": return Direction.LEFT;
            case "r": return Direction.RIGHT;
            default:
                throw new IllegalArgumentException("Direcao invalida: " + letter);
        }
    }

    public Direction getDirection() {
        return direction;
    } 
}
