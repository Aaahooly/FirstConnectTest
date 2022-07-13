package Lessons3312;

public class People {
    public static void main(String[] args) {
        Human man = new Human();
        Human man1 = new Human((byte) 3, "Maks", "Glukhov", "Begotnya");
        Human man2 = new Human((byte) 25, "Andrey", "Glukhov");
        System.out.println();
    }


    public static class Human {
        private byte age;
        private String name;
        private String secondName;
        private String favoriteSport;

        public Human() {
        }

        public Human(byte age, String name, String secondName, String favoriteSport) {
            this(age, name, secondName);
            this.favoriteSport = favoriteSport;
        }

        public Human(byte age, String name, String secondName) {
            this.age = age;
            this.name = name;
            this.secondName = secondName;
        }
    }
}
