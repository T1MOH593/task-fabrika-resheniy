package by.vlad.test.task_fabrika_resheniy.dto;

public class AnswerDto {

    private Integer id;

    private String username;

    private String text;

    public AnswerDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
