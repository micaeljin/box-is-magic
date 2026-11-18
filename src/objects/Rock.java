package objects;
import pt.iscte.poo.utils.Point2D;

public class Rock extends PushableObject {
    public Rock(Point2D p) {
        super(p);
    }

    @Override 
    public String getName() {
        return "rock";
    }

    @Override 
    public int getLayer() {
        return 2;
    }
}
