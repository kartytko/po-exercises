package lab9SerwerWielowatkowy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Levensthein {

    static public String haslo = "marka";
    static public List<Character> char_list_tmp = new ArrayList<Character>();
    static public int haslo_length = haslo.length();
    static public void fillList(){
        //a-z
        for(char i=97; i<=122; i++){
            char_list_tmp.add(i);
        }
        //A-Z
        for(char i=65; i<=90; i++){
            char_list_tmp.add(i);
        }
        char_list_tmp.add((char)45); //-  45
        char_list_tmp.add((char)47); // /  47
        char_list_tmp.add((char)243); //ó  243
        char_list_tmp.add((char)261); //ą  261
        char_list_tmp.add((char)262); //Ć  262
        char_list_tmp.add((char)263); //ć  263
        char_list_tmp.add((char)281); //ę  281
        char_list_tmp.add((char)321); //Ł  321
        char_list_tmp.add((char)322); //ł  322
        char_list_tmp.add((char)324); //ń  324
        char_list_tmp.add((char)346); //Ś  346
        char_list_tmp.add((char)347); //ś  347
        char_list_tmp.add((char)378); //ź  378
        char_list_tmp.add((char)379); //Ż  379
        char_list_tmp.add((char)380); //ż  380

        char_list_tmp.add((char)261);

    }

    static public void main(String [] args){

        String has = "ariada";
        String has_next = has;
        int has_length = has.length();
        List<Character> char_list = new ArrayList<Character>();
        for(char i=97; i<=122; i++){
            char_list.add(i);
        }
        for(char i=65; i<=90; i++){
            char_list.add(i);
        }

        fillList();
        int distance = levenshteinDistance(haslo, has);
       // int has_length = has.length();
        int has_it = 0;

        System.out.println(char_list.size() + " "+ char_list_tmp.size()+char_list_tmp.get(0));
        int it=0;
        while(true){
            it++;
            System.out.println(has_next + " "+levenshteinDistance(haslo, has_next));
            if(has_it+1==has_length){
                break;
            }
            if(char_list_tmp.size()!=0){
                has_next = has.substring(0, has_it)+char_list_tmp.get(0)+has.substring(has_it+1, has.length());
                //System.out.println(it + " "+has_next);
            }else{
                has_it++;
                has_next=has;
                //char_list_tmp=char_list;
                System.out.println("ELDO");
                fillList();

                continue;
            }
            if(levenshteinDistance(haslo, has_next)>distance){
                System.out.println(it + " " +char_list.get(0)+" "+has_it+" levenstein wiekszy");
                has_next=has;
                char_list_tmp.remove(0);
                continue;
            }
            if(levenshteinDistance(haslo, has_next)<distance){
                System.out.println(it + " " +char_list.get(0)+" "+has_it+" levenstein mniejszy");
                has = has_next;
                has_it++;
                char_list_tmp=char_list;
                distance=levenshteinDistance(haslo, has_next);
            }else{
            //if(levenshteinDistance(haslo, has_next)){
                System.out.println(it + " " +char_list.get(0)+" "+has_it+" levenstein równy");
                char_list_tmp.remove(0);
            }

        }


        System.out.println("haselko "+ has_next +" "+ has);
/*        for(int i=0; i<has.length(); i++){
            has_next = has;
            has_next = has_next.substring(0, i)+"X"+has_next.substring(i+1, has_next.length());
            System.out.println(i + " " +has_next);
        }
*/

    }



    public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances

        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int cost_replace = cost[i - 1] + match;
                int cost_insert  = cost[i] + 1;
                int cost_delete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            // swap cost/newcost arrays
            int[] swap = cost; cost = newcost; newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

}
