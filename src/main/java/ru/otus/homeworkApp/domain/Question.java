package ru.otus.homeworkApp.domain;

import java.util.List;
import java.util.Objects;

public class Question {
    private final String question;
    private List<Answer> answers;

    public Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return
                "question='" + question + '\'' + "\n" +
                "answers=" + answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return question.equals(question1.question) &&
                answers.equals(question1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answers);
    }
}
