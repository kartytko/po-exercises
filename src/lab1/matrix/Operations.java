package lab1.matrix;

import java.util.Scanner;
import java.util.Random;

public class Operations{

    public static void main(String [] argv){
//tworzenie macierzy
        System.out.println("Podaj wymiary macierzy: ");
        Scanner rd = new Scanner (System.in);
        int div = rd.nextInt();
        int [][] array1 = new int[div][div];
        int [][] array2 = new int[div][div];

//wpisywanie losowych liczb do macierzy
        Random generator = new Random();
        for(int i=0; i<div; i++){
            for(int j=0; j<div; j++){
                array1[i][j] = generator.nextInt(50);
                array2[i][j] = generator.nextInt(50);
            }
        }
//wypisywanie macierzy
        System.out.println("Macierz 1:" );
        for(int i=0; i<div; i++){
            for(int j=0; j<div; j++){
                System.out.print(array1[i][j] + " ");
            }
            System.out.print('\n');
        }

        System.out.println('\n' + "Macierz 2:");
        for(int i=0; i<div; i++){
            for(int j=0; j<div; j++){
                System.out.print(array2[i][j] + " ");
            }
            System.out.print('\n');
        }



//wypisywanie wynikow operacji
        Macierz matrix = new Macierz(array1, div);
        int[][] matadd = matrix.add(array2);
        int[][] matsub = matrix.sub(array2);
        int[][] matmul = matrix.mul(array2);

    }
}