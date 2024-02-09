package misc;

@SuppressWarnings("unused")
public class Scoreboard1 {
    private int numEntries = 0;// number of actual entries
    private final GameEntry[] board; // array of game entries (names & scores)

    public Scoreboard1(int capacity) {
        board = new GameEntry[capacity];
    }

    public void add(GameEntry e) {
        int newScore = e.getScore();

        if (numEntries >= board.length || newScore <= board[numEntries - 1].getScore()) return;

        if (numEntries < board.length) numEntries++;


        int j = numEntries - 1;

        while (j > 0 && board[j-1].getScore() < newScore) {

            board[j] = board[j-1];

            j--;
        }

        board[j] = e;
    }

    /** Remove and return the high score at index i. */
    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= numEntries) throw new IndexOutOfBoundsException("Invalid index: " + i);

        GameEntry temp = board[i];
        for (int j = i; j < numEntries - 1; j++) {
            board[j] = board[j+1];
        }

        board[numEntries - 1] = null;
        numEntries--;

        return temp;
    }

    public static class GameEntry {
        private final int score;

        GameEntry(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }
}
