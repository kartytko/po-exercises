package lab3.zad5;

//import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;

import java.util.LinkedList;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailMessage {

    private String from;
    private LinkedList<String> to;
    private String subject;
    private String content;
    private String mimeType;
    private LinkedList<String> cc;
    private LinkedList<String> bcc;

    public String getFrom(){
        return from;
    }

    protected EmailMessage(){}
    public static Builder builder(){
        return new EmailMessage.Builder();
    }

    static public class Builder{
        private EmailMessage email = new EmailMessage();
        public Builder(){}

        public Builder addFrom(String from_){
            email.from = from_;
            return this;
        }

        public Builder addTo(String to_){
            if(email.to == null){
                email.to = new LinkedList<String>();
            }
            email.to.add (to_);
            return this;
        }

        public Builder addSubject(String subject_){
            email.subject = subject_;
            return this;
        }

        public Builder addContent (String content_){
            email.content = content_;
            return this;
        }

        public Builder addMimeType (String mimeType_){
            email.mimeType = mimeType_;
            return this;
        }

        public Builder addBuilderCC (String cc_){
            if(email.cc == null){
                email.cc = new LinkedList<String>();
            }

            email.cc.add(cc_);
            return this;
        }

        public Builder addBuilderbcc (String bcc_){
            if(email.bcc == null){
                email.bcc = new LinkedList<String>();
            }

            this.email.bcc.add(bcc_);
            return this;
        }

        public EmailMessage build(){
            return email;
        }

    }
    public void send(){
        final String username = this.from;
        final String password;


        Scanner ss = new Scanner(System.in);
        System.out.println("Podaj hasło");
        password = ss.nextLine();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));


            for(String a : this.to){
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(a));
            }

            message.setSubject(this.subject);
            message.setText(this.content);
            message.isMimeType(this.mimeType);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

