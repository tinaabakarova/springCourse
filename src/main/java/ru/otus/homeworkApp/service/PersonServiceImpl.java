package ru.otus.homeworkApp.service;

import org.springframework.stereotype.Service;
import ru.otus.homeworkApp.config.ApplicationPropertiesConfig;
import ru.otus.homeworkApp.domain.Person;

import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService{
    private Person person;
    private ApplicationPropertiesConfig applicationProperties;

    public PersonServiceImpl(Person person, ApplicationPropertiesConfig applicationProperties) {
        this.person = person;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void askPersonCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(applicationProperties.getMessageSource().getMessage("greetingPart1",
                new String[]{}, applicationProperties.getLocale()));
        person.setName(scanner.nextLine());
        System.out.println(applicationProperties.getMessageSource().getMessage("greetingPart2",
                new String[]{}, applicationProperties.getLocale()));
        person.setLastName(scanner.nextLine());
    }

    @Override
    public void greeting() {
        System.out.println(applicationProperties.getMessageSource().getMessage("greetingPart3",
                new String[]{person.getName(),person.getLastName()}, applicationProperties.getLocale()));
    }
}
