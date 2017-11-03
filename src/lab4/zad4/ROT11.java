package lab4.zad4;

public class ROT11 implements Algorithm {
    //public final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public final int alphabet_size = 26;
    public final char [] alphabet = new char[alphabet_size];
    public final int rot  = 13;

    public ROT11(){
        int iterator = 0;
        for(char character = 'a'; character <= 'z'; character++){
            alphabet[iterator]=character;
            iterator++;
        }
    }

    public String crypt(String word_){
        String crypted = new String();
        for(int i=0; i<word_.length(); i++){

            //lowercase
            if(word_.charAt(i)>= 97 && word_.charAt(i)<=122){
                crypted = crypted + alphabet[(word_.charAt(i)-97 + rot)%alphabet_size];
                continue;
            }

            //uppercase
            if(word_.charAt(i)>= 65 && word_.charAt(i)<=90){
                crypted = crypted +  (char)(alphabet[(word_.charAt(i)-65 + rot)%alphabet_size] - 32);
                continue;
            }

            //any other character
            crypted = crypted + word_.charAt(i);
        }

        return crypted;
    }

    public String decrypt(String word_){
        String decrypted = new String();

        for(int i=0; i<word_.length(); i++){

            //lowercase
            if(word_.charAt(i)>= 97 && word_.charAt(i)<=122){
                decrypted = decrypted + alphabet[(word_.charAt(i)-97 + (alphabet_size - rot))%alphabet_size];
                continue;
            }

            //uppercase
            if(word_.charAt(i)>= 65 && word_.charAt(i)<=90){
                decrypted = decrypted +  (char)(alphabet[(word_.charAt(i)-65 + (alphabet_size - rot))%alphabet_size]- 32);
                continue;
            }

            //any other character
            decrypted = decrypted + word_.charAt(i);
        }

        return decrypted;
    }

}
