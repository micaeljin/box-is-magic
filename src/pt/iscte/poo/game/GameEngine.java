package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;

import objects.Baba;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;

public class GameEngine implements Observer {

    private static GameEngine INSTANCE = new GameEngine();
    private static final String ROOMS_DIRECTORY = "rooms";

    private Room currentRoom;
    private int lastTickProcessed = 0;

    private GameEngine() {
        // currentRoom = Room.readRoom(new File(ROOMS_DIRECTORY + "/room0.txt"));
        loadRoom(0);
        // updateGUI();
    }

	public static GameEngine getInstance() {
		return INSTANCE;
	}

    public Room getCurrentRoom() { 
        return currentRoom; 
    }

    private void loadRoom(int n) {
    currentRoom = Room.readRoom(new File(ROOMS_DIRECTORY + "/room" + n + ".txt"));
    ImageGUI.getInstance().clearImages();
    ImageGUI.getInstance().addImages(currentRoom.getObjects());
}


    @Override
    public void update(Observed source) {

        if (ImageGUI.getInstance().wasKeyPressed()) {
            int k = ImageGUI.getInstance().keyPressed();

            if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN) {
                Direction d = Direction.directionFor(k);
                getPlayer().move(d);
                // updateGUI();
            }
        }
        int t = ImageGUI.getInstance().getTicks();
        while (lastTickProcessed < t) {
            processTick();
        }
        ImageGUI.getInstance().update();
    }

    private void processTick() {
        lastTickProcessed++;
    }

    public void updateGUI() {
        if (currentRoom != null) {
            // ImageGUI.getInstance().clearImages();
            // ImageGUI.getInstance().addImages(currentRoom.getObjects());
            GameEngine.getInstance().update(null);
        }
    }


    public Baba getPlayer() {
        return currentRoom.getPlayer();
    }
}
