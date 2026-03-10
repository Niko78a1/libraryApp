package dtu.library.app;

public interface EmailServer {

    public void sendEmail(String email, String subject, String text);
}