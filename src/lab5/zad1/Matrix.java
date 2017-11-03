package lab5.zad1;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;


public class Matrix{
    public int[][] matrix_;
    private int dim_;
    private int dim2_;

    public Matrix(int [][] matrix, int dim, int dim2){
        matrix_ = matrix;
        dim_ = dim;
        dim2_  = dim2;
    }

    public int GetDim_(){ return dim_; }
    public int GetDim2_(){ return dim2_; }
    public void SetDim(int a){dim_ = a;}
    public void SetDim2(int a){dim2_ = a;}
    public void SetMatrix(int [][] a ){matrix_ = a;}



    public Matrix(File file)throws IOException, FileNotFoundException, Exception {

        int counter = 0;
        Vector vector = new Vector<MyValue>();
        try {
            Scanner reader = new Scanner(file);
            int integer;

            while (reader.hasNextLine()) {
                integer = reader.nextInt();
                if(counter==0){
                    SetDim(integer);
                    counter++;
                    continue;
                }

                if(counter==1){
                    SetDim2(integer);
                    counter++;
                    continue;
                }

                if(counter==2){
                    vector.add(new MyValue(integer));
                }
            }


            try {
                SetMatrix(new int [dim_][dim2_]);

                if (dim_ * dim2_ > vector.size()) {
                    throw new TooFewArguments();
                }
                else if (dim_ * dim2_ < vector.size()) {
                    throw new TooManyArguments();
                }
                else {
                    counter = 0;
                    for(int i=0; i<dim_; i++){
                        for(int j=0; j<dim2_; j++){

                            lab5.zad1.MyValue tmp = (MyValue)vector.get(counter);
                            matrix_[i][j] = tmp.GetValue();
                            counter++;
                        }
                    }
                }

            }finally{
                reader.close();
            }

        }catch (FileNotFoundException e) {
            throw e;
        }
    }



    public int[][] add(Matrix matrix2) throws Exception{
        if(dim_!=matrix2.dim_ || dim2_ != matrix2.dim2_){
            throw new MatrixDimensionsException();
        }
        System.out.print('\n'+"Suma macierzy:"+'\n');
        int[][] matrix_tmp = new int [dim_][dim2_];
        for(int i=0; i<dim_; i++){
            for(int j=0; j<dim2_; j++){
                matrix_tmp[i][j] = matrix_[i][j] + matrix2.matrix_[i][j];
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }




    public int[][] sub(Matrix matrix2) throws Exception{
        if(dim_!=matrix2.dim_ || dim2_ != matrix2.dim2_){
            throw new MatrixDimensionsException();
        }

        System.out.print('\n'+"Roznica macierzy:"+'\n');

        int[][] matrix_tmp = new int [dim_][dim2_];
        for(int i=0; i<dim_; i++){
            for(int j=0; j<dim2_; j++){
                matrix_tmp[i][j] = matrix_[i][j] - matrix2.matrix_[i][j];
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }




    public int[][] mul(Matrix matrix2) throws Exception{
        if(dim2_ != matrix2.dim_ ){
            throw new MatrixDimensionsException();
        }

        int[][] matrix_tmp = new int [this.dim_][matrix2.dim2_];
        for(int i=0; i<this.dim_; i++){
            for(int j=0; j<matrix2.dim2_; j++){
                matrix_tmp[i][j]=0;
            }
        }


        System.out.print('\n'+"Iloczyn macierzy:"+'\n');
        for(int i=0; i<this.dim_; i++){
            for(int j=0; j<matrix2.dim2_; j++){
                for(int k=0; k<matrix2.dim_; k++){
                    matrix_tmp[i][j] = matrix_tmp[i][j]+ matrix_[i][k] * matrix2.matrix_[k][j];
                }
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }




    public void print(){
        for(int i=0; i<this.dim_; i++){
            for(int j=0; j<this.dim2_; j++){
                System.out.print(this.matrix_[i][j] + " ");
            }
            System.out.print('\n');
        }
    }

}
