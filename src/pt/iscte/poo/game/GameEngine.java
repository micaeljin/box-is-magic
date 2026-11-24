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

    private int move = 0;
    private int n = 0;

    private boolean restartRequested = false;
    private boolean nextLevelRequested = false;
    private boolean gameOver = false;

    private GameEngine() {
        loadRoom();
    }

	public static GameEngine getInstance() {
		return INSTANCE;
	}

    public Room getCurrentRoom() { 
        return currentRoom; 
    }

    private void loadRoom() {
        currentRoom = Room.readRoom(new File(ROOMS_DIRECTORY + "/room" + n + ".txt"));
        ImageGUI.getInstance().clearImages();
        ImageGUI.getInstance().addImages(currentRoom.getObjects());
        updateStatus();
    }

    public void requestRestart() {
        restartRequested = true;
    }

    public void requestNextLevel() {
        nextLevelRequested = true;
    }

    public void updateStatus() {
        ImageGUI.getInstance().setStatusMessage("Level: " + n + " Moves: " + move);
    }

    @Override
    public void update(Observed source) {

        if (ImageGUI.getInstance().wasKeyPressed() && !gameOver) {
            int k = ImageGUI.getInstance().keyPressed();

            if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN) {
                Direction d = Direction.directionFor(k);
                
                if (getPlayer().move(d)) {
                    move += 1;
                    updateStatus();
                }
            } else if (k == KeyEvent.VK_R) {
                requestRestart();
            }
        }


        if (restartRequested) {
            restartRequested = false;
            loadRoom();
        }

        if (nextLevelRequested) {
            nextLevelRequested = false;
            n++;

            if (new File(ROOMS_DIRECTORY + "/room" + n + ".txt").exists()) {
                loadRoom();
            } else {
                ImageGUI.getInstance().showMessage("Fim", "Ganhaste o jogo!");
                gameOver = true;
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

    public Baba getPlayer() {
        return currentRoom.getPlayer();
    }
}
