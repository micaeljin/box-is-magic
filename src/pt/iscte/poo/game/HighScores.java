package pt.iscte.poo.game;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;;

public class HighScores {
    private final static int MAX_SCORES = 10;
    private final File file;
    private final List<Score> scores = new ArrayList<>();
    
    public HighScores(String fileName) {
        file = new File(fileName);
    }

    public void load() {
        scores.clear();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                try {
                    scores.add(Score.fromFileLine(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("score ignorado: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nao foi possivel ler o ficheiro");
        } finally {
            if (sc != null) sc.close();
        }

        scores.sort(null);
    }

    public void add(Score s) {
        scores.add(s);
        scores.sort(null);
        if (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1);
        }
    }

    public void save() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            for (Score s: scores) {
                pw.println(s.toFileLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nao foi possivel guardar os highscores");
        } finally {
            if (pw != null) pw.close();
        }
    }

    public String showHighScores() {
        String results = "";

        int i = 1;
        for (Score s: scores) {
            results += i++ + ". Moves: " + s.getMoves() + " Time: " + s.getSeconds() + "s\n";
        }

        return results;
    }
}
