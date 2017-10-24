package lab3.zad5;


public class MainZad5 {
    public static void main(String [] args){

        EmailMessage wiadomosc = EmailMessage.builder()
                .addFrom("adres1@gmail.com")
                .addTo("adres2@op.pl")
                .addSubject("Mail testowy")
                .addContent("Brak tresci")
                .build();

        System.out.println(wiadomosc.getFrom());
        wiadomosc.send();
    }
}

