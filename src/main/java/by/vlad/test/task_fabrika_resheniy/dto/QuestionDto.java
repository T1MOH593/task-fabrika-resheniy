package by.vlad.test.task_fabrika_resheniy.dto;

public class QuestionDto {

    private Integer id;

    private String type;

    private String text;

    public QuestionDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
