package pt.iscte.poo.game;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import objects.Baba;
import objects.GameObject;
import objects.Tickable;
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
    private int n = 2;

    private boolean restartRequested = false;
    private boolean nextLevelRequested = false;
    private boolean gameOver = false;

    private RuleManager rules = new RuleManager();
    private HighScores highscores = new HighScores("src/pt/iscte/poo/game/highscores.txt");
    private double startTime;

    private GameEngine() {
        loadRoom();
        startTime =  ImageGUI.getInstance().getTicks();
    }

	public static GameEngine getInstance() {
		return INSTANCE;
	}

    public Room getCurrentRoom() { 
        return currentRoom; 
    }

    public RuleManager getRules() {
        return rules;
    }

    private void loadRoom() {
        File f = new File(ROOMS_DIRECTORY + "/room" + n + ".txt");
        if (!f.exists()) {
            endGame();
            return;
        }

        try {
            currentRoom = Room.readRoom(f);
            ImageGUI.getInstance().clearImages();
            ImageGUI.getInstance().addImages(currentRoom.getObjects());
            updateStatus();
            
            highscores.load();
        } catch (IOException | NumberFormatException e) {
            ImageGUI.getInstance().showMessage("Nivel invalido", "Nivel invalido");
            ImageGUI.getInstance().showMessage("Nivel invalido", e.getMessage());
            n++;
            loadRoom();
        }
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

    public void endGame() {
        gameOver = true;
        double endTime = ImageGUI.getInstance().getTicks() - startTime;
        highscores.add(new Score(move, endTime));
        ImageGUI.getInstance().showMessage("Ganhaste o jogo!", highscores.showHighScores());
        highscores.save();
    }

    @Override
    public void update(Observed source) {

        if (ImageGUI.getInstance().wasKeyPressed() && !gameOver) {
            int k = ImageGUI.getInstance().keyPressed();

            if (k == KeyEvent.VK_LEFT || k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_UP || k == KeyEvent.VK_DOWN) {
                Direction d = Direction.directionFor(k);
                
                if (getPlayer().move(d)) {
                    move += 1;
                    
                    rules.update(currentRoom);
                    if (rules.hasConflict()) {
                        requestRestart();
                    }

                    updateStatus();
                }
            } else if (k == KeyEvent.VK_R) {
                requestRestart();
            }
        }


        if (currentRoom != null && !gameOver) {
            int t = ImageGUI.getInstance().getTicks();
            while (lastTickProcessed < t) {
                processTick();
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
                endGame();
            }
        }

        ImageGUI.getInstance().update();
    }

    private void processTick() {
        lastTickProcessed++;

        for (GameObject o: new ArrayList<>(currentRoom.getObjects())) {
            if (o instanceof Tickable) {
                ((Tickable) o).tick();
            }
        }

        rules.update(currentRoom);
        if (rules.hasConflict()) {
            requestRestart();
        }
    }

    public Baba getPlayer() {
        return currentRoom == null ? null : currentRoom.getPlayer();
    }
}
