package pt.iscte.poo.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import objects.Baba;
import objects.Dirt;
import objects.GameObject;
import pt.iscte.poo.utils.Point2D;

import java.util.Scanner;
import java.util.Vector;
import pt.iscte.poo.utils.Direction;

import objects.GameCharacter;
import objects.Grass;
import objects.Wall;
import objects.CrackedWall;
import objects.Hole;

public class Room {

    private List<GameObject> objects;
    private String roomName;
    private Baba player;

    public Room() {
        objects = new ArrayList<>();
    }

    private void setName(String name) {
        roomName = name;
    }

    public String getName() {
        return roomName;
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    private List<GameObject> getObjects(Point2D position) {
        List<GameObject> res = new ArrayList<>();
        for (GameObject ob: objects) {
            if (ob.getPosition().equals(position)) {
                    res.add(ob);
                }
        }
        return res;
    }

    public boolean canMove(Point2D position) {
        List<GameObject> objs = getObjects(position);
        for (GameObject obj: objs) {
            if (!obj.isTransposable()) {
                return false;
            }
        }
        return true;
    }


    private static GameObject createStaticObject(char c, Point2D p) {
        if (c == ' ') {
            return new Dirt(p);
        } else if (c == 'X') {
            return new Wall(p);
        } else if (c == 'x') {
            return new CrackedWall(p);
        } else if (c == 'g') {
            return new Grass(p);
        } else if (c == 'H') {
              return new Hole(p);
        } else {
            return null;
        }
    }

    private static GameObject createMovableObject(String s, Point2D p) {
        // if (s.equals("rock")) {
        //     return new Rock(p);
        // } else if (s.equals("flag")) {
        //     return new Flag(p);
        // } else if (s.equals("box")) {
        //     return new Box(p);
        // } else if (s.equals("bomb")) {
        //     return new Bomb(p);
        // } else if (s.equals("wordIs")) {
        //     return new WordIs(p);
        // } else if (s.equals("wordBox")) {
        //     return new WordBox(p);
        // } else if (s.equals("wordStop")) {
        //     return new WordStop(p);
        // } else if (s.equals("wordMove")) {
        //     return new WordMove(p);
        // } else if (s.equals("wordWin")) {
        //     return new WordWin(p);
        // }

        return null;
    }
    


    public static Room readRoom(File f) {
        Room r = new Room();
        r.setName(f.getName());

        Scanner sc = null; 
        try {
            sc = new Scanner(f);
            int rows = 0;
            int cols = 0;
            if (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                rows = Integer.parseInt(parts[1]);
                cols = Integer.parseInt(parts[0]);
            }

            if (rows <= 0 || cols <= 0) {
                System.out.println("Dimensoes de mapa invalida");
                return null;
            }
            
            // ler os objetos estaveis
            for (int i = 0; i < rows; i++) {
                String line = sc.nextLine();
                int j = 0;
                for (char c: line.toCharArray()) {
                    r.addObject(new Dirt(new Point2D(i, j)));
                    GameObject object = createStaticObject(c, new Point2D(j, i));
                    j++;

                    if (object != null) {
                        r.addObject(object);
                    }
                }
            }


            // ler os objetos moveis
            while (sc.hasNext()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                Point2D p = new Point2D(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

                if (parts[0].equals("baba")) {
                    // r.player = new Baba(p, parts[3]);
                    r.player = new Baba(p, GameCharacter.directionFromLetter(parts[3]));
                    r.addObject(r.player);
                } else {
                    GameObject object = createMovableObject(parts[0], p);
                    if (object != null) {
                        r.addObject(object);
                    }
                }
            }

        } catch(Exception e) {
            System.out.println("Ficheiro Invalido");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return r;
    }

    public Baba getPlayer() {
        return player;
    }
}