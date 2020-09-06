package ru.otus.homeworkApp.domain;

import java.util.Objects;

public class Answer {
    private final String answer;
    private boolean isRight;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    @Override
    public String toString() {
        return "'" + answer + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer1 = (Answer) o;
        return isRight == answer1.isRight &&
                answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, isRight);
    }
}
