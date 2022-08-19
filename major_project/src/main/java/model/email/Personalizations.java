package model.email;

public class Personalizations {
    private To[] to;
    private String subject;

    public Personalizations(To[] to, String subject) {
        this.to = to;
        this.subject = subject;
    }
}
