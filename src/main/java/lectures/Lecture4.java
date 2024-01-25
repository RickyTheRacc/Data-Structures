package lectures;

public class Lecture4 {
    public static void main(String[] args) {
        Doctor d = new Doctor();
        Patient georgia = new Patient("Georgia", 71);
        d.checkup(georgia);
        System.out.println(georgia.height);
    }

    static class Patient {
        String name;
        int height;

        void updateHeight(int newHeight) {
            height = newHeight;
        }

        Patient(String n, int h) {
            name = n;
            height = h;
        }
    }

    static class Doctor {
        void checkup(Patient p) {
            Patient p2 = new Patient(p.name, p.height + 10);
            p.updateHeight(p.height + 10);
        }
    }

    record GameEntry(int score) {
        int getScore() { return score; }
    }

    /**
     * Class for storing high scores in an array in nondecreasing order.
     */
    static class Scoreboard {
        private int numEntries = 0;       // number of actual entries
        private GameEntry[] board;        // array of game entries (names & scores)

        /**
         * Constructs an empty scoreboard with the given capacity for storing entries.
         */
        public Scoreboard(int capacity) {
            board = new GameEntry[capacity];
        }

        /**
         *  Attempt to add a new score to the collection (if it is high enough)
         */
        public void add(GameEntry e) {
            int newScore = e.getScore();

            // is the new entry e really a high score?
            if (numEntries < board.length || newScore > board[numEntries-1].getScore()) {

                if (numEntries < board.length) {   // no score drops from the board
                    numEntries++;            // so overall number increases
                }

                // shift any lower scores rightward to make room for the new entry
                int j = numEntries - 1;
                while (j > 0 && board[j-1].getScore() < newScore) {
                    board[j] = board[j-1];       // shift entry from j-1 to j
                    j--;                // and decrement j
                }
                board[j] = e;             // when done, add new entry
            }
        }

 /**
  * Remove and return the high score at index i.
  */
        public GameEntry remove(int i) throws IndexOutOfBoundsException {
            if (i < 0 || i >= numEntries) {
                throw new IndexOutOfBoundsException("Invalid index: " + i);
            }

            GameEntry temp = board[i];         // save the object to be removed
            for (int j = i; j < numEntries - 1; j++) { // count up from i (not down)
                board[j] = board[j+1];          // move one cell to the left
            }

            board[numEntries -1 ] = null;       // null out the old last score
            numEntries--;
            return temp;                // return the removed object
        }
    }
}
