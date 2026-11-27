package pt.iscte.poo.game;

public class Score implements Comparable<Score> {
    private final int moves;
    private final double seconds;

    public Score(int moves, double seconds) {
        this.moves = moves;
        this.seconds = seconds;
    }

    public int getMoves() {
        return moves;
    }

    public double getSeconds() {
        return seconds;
    }

    @Override 
    public int compareTo(Score other) {
        int byMoves = Integer.compare(moves, other.moves);
        if (byMoves != 0) {
            return byMoves;
        }
        return Double.compare(seconds, other.seconds);
    }

    public String toFileLine() {
        return moves + ";" + seconds;
    }

    public static Score fromFileLine(String line) {
        String[] parts = line.split(";");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Linha de highscore invalida: " + line);
        }
        return new Score(Integer.parseInt(parts[0]), Double.parseDouble(parts[1]));
    }
}
