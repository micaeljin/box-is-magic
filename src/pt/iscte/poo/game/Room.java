package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objects.Baba;
import objects.Dirt;
import objects.GameObject;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

import java.util.Scanner;

import objects.GameCharacter;
import objects.Grass;
import objects.Wall;
import objects.CrackedWall;
import objects.Hole;

import objects.Rock;
import objects.Flag;
import objects.Box;
import objects.Bomb;
import objects.WordBox;
import objects.WordIs;
import objects.WordMove;
import objects.WordStop;
import objects.WordWin;

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

    public void addToGame(GameObject obj) {
        objects.add(obj);
        ImageGUI.getInstance().addImage(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
        ImageGUI.getInstance().removeImage(obj);
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public List<GameObject> getObjectsAt(Point2D position) {
        List<GameObject> res = new ArrayList<>();
        for (GameObject ob: objects) {
            if (ob.getPosition().equals(position)) {
                    res.add(ob);
                }
        }
        return res;
    }

    public boolean canMove(Point2D position) {
        List<GameObject> objs = getObjectsAt(position);
        for (GameObject obj: objs) {
            if (!obj.isTransposable()) {
                return false;
            }
        }
        return true;
    }

    private static GameObject createStaticObject(char c, Point2D p) {
        if (c == 'X') {
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
        if (s.equals("rock")) {
            return new Rock(p);
        } else if (s.equals("flag")) {
            return new Flag(p);
        } else if (s.equals("box")) {
            return new Box(p);
        } else if (s.equals("bomb")) {
            return new Bomb(p);
        } else if (s.equals("wordIs")) {
            return new WordIs(p);
        } else if (s.equals("wordBox")) {
            return new WordBox(p);
        } else if (s.equals("wordStop")) {
            return new WordStop(p);
        } else if (s.equals("wordMove")) {
            return new WordMove(p);
        } else if (s.equals("wordWin")) {
            return new WordWin(p);
        } else {
            return null;
        }
    }
    


    public static Room readRoom(File f) throws FileNotFoundException, IOException {
        Room r = new Room();
        r.setName(f.getName());

        Scanner sc = null; 
        try {
            sc = new Scanner(f);

            if (!sc.hasNextLine()) {
                throw new IOException("ficheiro vazio");
            }

            String[] parts = sc.nextLine().trim().split(",");
            if (parts.length != 2) {
                throw new IOException("linha das dimensoes invalida");
            }
            
            int rows = Integer.parseInt(parts[1]);
            int cols = Integer.parseInt(parts[0]);
            
            if (rows <= 0 || cols <= 0) {
                throw new IOException("dimensoes invalidas");
            }

            // int rows = 0;
            // int cols = 0;
            // if (sc.hasNextLine()) {
            //     String[] parts = sc.nextLine().split(",");
            //     rows = Integer.parseInt(parts[1]);
            //     cols = Integer.parseInt(parts[0]);
            // }

            // if (rows <= 0 || cols <= 0) {
            //     System.out.println("Dimensoes de mapa invalida");
            //     return null;
            // }

            
            // ler os objetos estaticos
            for (int y = 0; y < rows; y++) {

                if (!sc.hasNextLine()) {
                    throw new IOException("o mapa tem menos de " + rows + " linhas");
                }
                
                String line = sc.nextLine();
                int x = 0;
                for (char c: line.toCharArray()) {
                    Point2D p = new Point2D(x, y);
                    r.addObject(new Dirt(p));
                    GameObject object = createStaticObject(c, p);
                    x++;
                    
                    if (object != null) {
                        r.addObject(object);
                    }
                }

                if (x != cols) {
                    throw new IOException("o mapa tem menos de " + cols + " colunas");
                }
            }


            // ler os objetos moveis
            // while (sc.hasNext()) {
            //     String line = sc.nextLine().trim();
            //     if (line.isEmpty()) continue;
                
            //     String[] parts = line.split(",");
            //     Point2D p = new Point2D(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

            //     if (parts[0].equals("baba")) {
            //         r.player = new Baba(p, GameCharacter.directionFromLetter(parts[3]));
            //         r.addObject(r.player);
            //     } else {
            //         GameObject object = createMovableObject(parts[0], p);
            //         if (object != null) {
            //             r.addObject(object);
            //         }
            //     }
            // }

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                parts = line.split(",");
                String name = parts[0];
                boolean isBaba = name.equals("baba");

                if ((isBaba && parts.length != 4) || (!isBaba && parts.length != 3)) {
                    throw new IOException("numero de campos errado em " + line);
                }

                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                if (x < 0 || x >= cols || y < 0 || y >= cols) {
                    throw new IOException("posicao (" + x + "," + y + ") fora do mapa");
                }

                Point2D p = new Point2D(x, y);

                if (isBaba) {
                    if (r.player != null) {
                        throw new IOException("o nivel tem mais do que uma baba");
                    }
                    try {
                        r.player = new Baba(p, GameCharacter.directionFromLetter(parts[3]));
                    } catch (IllegalArgumentException e) {
                        throw new IOException("direcao desconhecida");
                    }
                    r.addObject(r.player);
                } else {
                    GameObject object = createMovableObject(name, p);
                    if (object == null) {
                        throw new IOException("objeto desconhecido");
                    }
                    r.addObject(object);
                }
            }

            
            if (r.player == null) {
                throw new IOException("o nivel nao tem baba");
            }

        } catch(IOException | NumberFormatException e) {
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