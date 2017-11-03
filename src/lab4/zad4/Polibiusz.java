package lab4.zad4;

public class Polibiusz implements Algorithm{

    public String crypt (String word_) {
        String crypted = new String();
        String word = word_.toLowerCase();
        int first = 0;
        int second = 0;
        int character_index = 0; // 'a' is 0, 'b' is 1.... 'z' is 24
        for(int i=0; i<word.length(); i++){

            if(word.charAt(i) >= 97 && word.charAt(i) <= 122){

                character_index = word.charAt(i)-97;
                if(word.charAt(i)>105){character_index--;}

                first = (int)(character_index/5) + 1;
                second = (character_index)%5 + 1;

                crypted = crypted + (char)(first+48)+(char)(second+48);
            }else{
                crypted = crypted + word.charAt(i);
            }
        }

        return crypted;
    }


    public String decrypt (String word_){

        String decrypted = new String();
        int counter = 0;
        int first = 0;
        int second = 0;
        int letter = 0;
        for(int i=0; i<word_.length(); i++){
            if(word_.charAt(i)>= 48 && word_.charAt(i)<=57){
                if(counter%2==0){
                    first = (int)(word_.charAt(i))-48;
                    second = (int)(word_.charAt(i+1))-48;

                    letter = (first-1)*5+second;
                    if(letter>9){letter++;};

                    decrypted = decrypted+(char)(letter+96);
                }
                counter++;
            }else{
                decrypted=decrypted+word_.charAt(i);}
        }

        return decrypted;
    }
}
