package lab1.pesel;

import java.util.Arrays;

public class PESEL{
    private static String pesel_ = null;

    public 	PESEL(String pesel){
        pesel_ = pesel;
    }

//9×a + 7×b + 3×c + 1×d + 9×e + 7×f + 3×g + 1×h + 9×i + 7×j

    public static boolean check(){
        char single_number;
        int sum = 0;
        int[] checkerArray = {9, 7, 3, 1, 9, 7, 3, 1, 9, 7};
        int[] peselArray = new int[11];

        for(int i=0; i<10; i++){
            single_number = pesel_.charAt(i);
            sum = sum + (single_number-48)*checkerArray[i];
        }

        single_number = pesel_.charAt(10);

        if(sum%10 == single_number-48){
            return true;
        }else{
            return false;
        }

    }
}