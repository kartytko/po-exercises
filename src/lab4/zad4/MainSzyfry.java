package lab4.zad4;

import java.io.IOException;

public class MainSzyfry {

    public static void main(String [] args) throws IOException{
    Polibiusz szyfr = new Polibiusz();
    Cryptographer Krypto = new Cryptographer();
    Krypto.cryptfile("/home/kartytko/Pulpit/plik1", "/home/kartytko/Pulpit/plik2", szyfr);
    Krypto.decryptfile("/home/kartytko/Pulpit/plik2", "/home/kartytko/Pulpit/plik3", szyfr);
    }

}
