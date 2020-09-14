package ru.otus.homeworkApp.service;

import ru.otus.homeworkApp.domain.Person;

import java.util.Scanner;

public class PersonServiceImpl implements PersonService{
    private Person person;

    public PersonServiceImpl(Person person) {
        this.person = person;
    }

    @Override
    public void askPersonCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dear user, please enter your first name:");
        person.setName(scanner.nextLine());
        System.out.println("Great! Now please, enter your last name:");
        person.setLastName(scanner.nextLine());
    }

    @Override
    public void greeting() {
        System.out.println("Hi " + person.getName() + " " + person.getLastName() + "!");
    }
}
