package pt.iscte.poo.game;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import objects.Baba;
import objects.Dirt;
import objects.GameObject;
import pt.iscte.poo.utils.Point2D;

import java.util.Scanner;

import objects.Grass;
import objects.Wall;
import objects.CrackedWall;
import objects.Hole;

public class Room {

    private List<GameObject> objects;
    private String roomName;
    private Baba player;
    private static Room INSTANCE = new Room();

    private Room() {
        objects = new ArrayList<>();
    }

    public static Room getInstance() {
        return INSTANCE;
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
            if (ob.getPosition().getX() == position.getX() &&
                ob.getPosition().getY() == position.getY()) {
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



    private static GameObject createObject(char c, int i, int j) {
        if (c == ' ') {
            GameObject dirt = new Dirt(new Point2D(i, j));
            return dirt;
        } else if (c == 'X') {
            GameObject wall = new Wall(new Point2D(i, j));
            return wall;
        } else if (c == 'x') {
            GameObject crackedWall = new CrackedWall(new Point2D(i, j));
            return crackedWall;
        } else if (c == 'g') {
            GameObject grass = new Grass(new Point2D(i, j));
            return grass;
        } else if (c == 'H') {
            GameObject hole = new Hole(new Point2D(i, j));
            return hole;
        } else {
            return null;
        }
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

            if (rows <= 0 && cols <= 0) {
                System.out.println("Dimensoes de mapa invalida");
                return null;
            }

            for (int i = 0; i < rows; i++) {
                String line = sc.nextLine();
                int j = 0;
                for (char c: line.toCharArray()) {
                    GameObject object = createObject(c, j, i);
                    j++;

                    if (object != null) {
                        r.addObject(object);
                    }
                }

            }


        } catch(Exception e) {
            System.out.println("Ficheiro Invalido");
        } finally {
            sc.close();
        }



        Baba baba = new Baba(new Point2D(2, 3));
        r.addObject(baba);
        r.player = baba;
        return r;






        // Room r = new Room();
        // r.setName(f.getName());

        // for (int i = 0; i <= 10; i++) {
        //     ImageTile dirt = new Dirt(new Point2D(i, i));
        //     r.addObject(dirt);
        //     ImageTile dirt2 = new Dirt(new Point2D(10 - i, i));
        //     r.addObject(dirt2);
        // }

        // Baba baba = new Baba(new Point2D(5, 5));
        // r.addObject(baba);

        // r.player = baba;
        // return r;
    }

    public Baba getPlayer() {
        return player;
    }

}