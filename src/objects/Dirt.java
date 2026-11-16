package objects;

// import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Dirt extends StaticObject {

    private Point2D position;

    public Dirt(Point2D position) {
        super(position);
        // this.position = position;
    }

    @Override
    public String getName() {
        return "dirt";
    }

    // @Override
    // public Point2D getPosition() {
    //     return position;
    // }

    @Override
    public int getLayer() {
        return 5;
    }
}
