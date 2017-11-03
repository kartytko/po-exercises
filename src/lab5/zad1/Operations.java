package lab5.zad1;

import lab5.zad1.Macierz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Operations{

    public static void main(String [] argv) throws Exception, IOException{

        //wczytywanie macierzy z pliku
        Matrix m1 = new Matrix(new File("/home/kartytko/Pulpit/java-exercises/src/lab5/zad1/data"));
        m1.print();



        int div=3;
        int div2=2;
        int div3=2;
        int div4=5;
        int [][] array1 = new int[div][div2];
        int [][] array2 = new int[div3][div4];

        //wpisywanie losowych liczb do macierzy
        Random generator = new Random();
        for(int i=0; i<div; i++){
            for(int j=0; j<div2; j++){
                array1[i][j] = generator.nextInt(5)+1;
            }
        }

        for(int i=0; i<div3; i++){
            for(int j=0; j<div4; j++){
                array2[i][j] = generator.nextInt(5)+1;
            }
        }

        //wypisywanie wynikow operacji
        lab5.zad1.Matrix matrix = new Matrix(array1, div, div2);
        System.out.println("Macierz 1: \n");
        matrix.print();

        lab5.zad1.Matrix matrix2 = new Matrix(array2, div3, div4);
        System.out.println("Macierz 2: \n");
        matrix2.print();

        //int[][] matadd = matrix.add(matrix2);
        //int[][] matsub = matrix.sub(matrix2);
        int[][] matmul = matrix.mul(matrix2);

    }
}