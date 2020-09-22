package ru.otus.homeworkApp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class ApplicationPropertiesConfig {
    private int answersCountToPass;
    private Resource questions;
    private Resource answers;
    private Resource rightAnswers;
    private Resource questionsRu;
    private Resource answersRu;
    private Resource rightAnswersRu;
    private Locale locale;
    private final MessageSource messageSource;

    public ApplicationPropertiesConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public int getAnswersCountToPass() {
        return answersCountToPass;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setAnswersCountToPass(int answersCountToPass) {
        this.answersCountToPass = answersCountToPass;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Resource getQuestions() {
        return questions;
    }

    public void setQuestions(Resource questions) {
        this.questions = questions;
    }

    public Resource getAnswers() {
        return answers;
    }

    public void setAnswers(Resource answers) {
        this.answers = answers;
    }

    public Resource getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Resource rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public Resource getQuestionsRu() {
        return questionsRu;
    }

    public void setQuestionsRu(Resource questionsRu) {
        this.questionsRu = questionsRu;
    }

    public Resource getAnswersRu() {
        return answersRu;
    }

    public void setAnswersRu(Resource answersRu) {
        this.answersRu = answersRu;
    }

    public Resource getRightAnswersRu() {
        return rightAnswersRu;
    }

    public void setRightAnswersRu(Resource rightAnswersRu) {
        this.rightAnswersRu = rightAnswersRu;
    }
}
