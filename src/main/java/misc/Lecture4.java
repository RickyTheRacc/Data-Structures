package misc;

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
}
