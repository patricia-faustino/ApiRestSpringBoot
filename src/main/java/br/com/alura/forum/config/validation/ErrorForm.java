package br.com.alura.forum.config.validation;

public class ErrorForm {

    private String field;
    private String message;

    public ErrorForm(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
