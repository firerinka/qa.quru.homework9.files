package quru.qa.domain;

public class Email {
    private String sender;
    private String[] receivers;
    private String subject;
    private Attach[] attaches;
    private String text;
    private Boolean isSpam;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Attach[] getAttaches() {
        return attaches;
    }

    public void setAttaches(Attach[] attaches) {
        this.attaches = attaches;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsSpam() {
        return isSpam;
    }

    public void setIsSpam(Boolean spam) {
        isSpam = spam;
    }
}
