package by.vlad.test.task_fabrika_resheniy.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private LocalDate startDate;

    private LocalDate finishDate;

    private String description;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "poll",
            fetch = FetchType.LAZY)
    private List<Question> questions;

    public Poll() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return Objects.equals(id, poll.id) && Objects.equals(title, poll.title) && Objects.equals(startDate, poll.startDate) && Objects.equals(finishDate, poll.finishDate) && Objects.equals(description, poll.description) && Objects.equals(questions, poll.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startDate, finishDate, description, questions);
    }
}
