import java.util.ArrayList;
import java.util.List;

public class People {
    private Integer age;
    private String name;
    private String familyName;

    public static void main(String[] args) {
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People(28, "Oleg", "Glukhov"));
        peopleList.add(new People(24, "Masha", "Zayceva"));
        peopleList.add(new People(26, "Andrey", "Glukhov"));
        peopleList.add(new People(30, "Vitaliya", "Vasyliewa"));
        peopleList.add(new People(4, "Maksim", "Glukhov"));
        peopleList.add(new People(50, "Elena", "Hromova"));
        peopleList.add(new People(54, "Nikolay", "Glukhov"));
        System.out.println(peopleList);
        peopleList.stream().sorted((a,b) -> {
            if (a.getAge() < b.getAge()) {
                return 1;
            } else if (a.getAge() > b.getAge()) {
                return -1;
            } else {
                return 0;
            }
        }).forEach(System.out::println);
    }

    public People( Integer age, String name, String familyName){
            this.age = age;
            this.name = name;
            this.familyName = familyName;
        }

        public Integer getAge () {
            return age;
        }

        @Override
        public String toString () {
            return age + " " + name + " " +
                    familyName;
        }

        public String getName () {
            return name;
        }

        public String getFamilyName () {
            return familyName;
        }
    }

