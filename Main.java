package lessons.javaCore.stream2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = addPerson();
        //printPerson(people);

        //Кол-во людей младше 18 лет:
        System.out.println("Люди младше 18 лет: " +
                people.stream()
                        .filter(person -> person.getAge() < 18)
                        .count()
        );
        System.out.println();

        //Кол-во мужчин, которым 18-27 лет
        System.out.println("Мужчины от 18 до 27 лет (призывники): " +
                        people.stream()
                                .filter(person -> person.getAge() > 18)
                                .filter(person -> person.getAge() < 27)
                                .filter(person -> person.getSex().equals(Sex.MAN))
                                .count()
                );

        //Список мужчин, которым 18-27 лет
        people.stream()
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .forEach(person -> System.out.println("- " + person.getFamily()));
        System.out.println();

        //Список потенциально работоспособных людей:
        System.out.println("Потенциально работоспособные люди:");
        List<Person> jobPeople = new ArrayList<>();
        people.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getSex().equals(Sex.MAN))
                .forEach(jobPeople::add);
        people.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 60)
                .filter(person -> person.getSex().equals(Sex.WOMAN))
                .forEach(jobPeople::add);
        jobPeople.stream()
                .sorted(Comparator.comparing(Person::getFamily)
                        .thenComparing(Person::getName)
                        .thenComparing(Person::getAge))
                .forEach(person -> System.out.println("- " + person));
    }

    public static void printPerson(List<Person> people) {
        int num = 1;
        for (Person person : people) {
            System.out.println("[" + num++ + "]" + person);
        }
    }

    public static List<Person> addPerson() {
        Random random = new Random();
        List<Person> people = new ArrayList<>();
        List<String> namesMan = Arrays.asList("Леха", "Саня", "Макс", "Петя", "Конор");
        List<String> namesWoman = Arrays.asList("Лена", "Катя", "Маша", "Лиза", "Алиса");
        List<String> surnameMan = Arrays.asList("Иванов", "Петров", "Сидоров", "Григорьев", "Грегор");
        List<String> surnameWoman = Arrays.asList("Иванова", "Петрова", "Сидорова", "Григорьева", "Грегоровна");
        for (int i = 0; i < 50; i++) {
            if (random.nextInt(2) == 1) {
                people.add(new Person(
                        namesMan.get(random.nextInt(namesMan.size())),
                        surnameMan.get(random.nextInt(surnameMan.size())),
                        random.nextInt(80) + 6,
                        Sex.MAN,
                        Education.values()[random.nextInt(Education.values().length)]
                ));
            } else {
                people.add(new Person(
                        namesWoman.get(random.nextInt(namesMan.size())),
                        surnameWoman.get(random.nextInt(surnameMan.size())),
                        random.nextInt(80) + 6,
                        Sex.WOMAN,
                        Education.values()[random.nextInt(Education.values().length)]
                ));
            }
        }
        return people;
    }
}
