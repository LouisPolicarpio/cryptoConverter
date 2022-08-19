package model.email;

public class Email {
    private Personalizations[] personalizations;
    private String subject;
    private Content[] content;

    private From from;
    private Reply_to reply_to;

    public Email(Personalizations[] personalizations, String subject, Content[] content, From from, Reply_to reply_to) {
        this.personalizations = personalizations;
        this.subject = subject;
        this.content = content;
        this.from = from;
        this.reply_to = reply_to;
    }
}
