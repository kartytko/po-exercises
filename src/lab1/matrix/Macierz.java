package lab1.matrix;

public class Macierz{
    private int[][] matrix_;
    private int div_;
    public Macierz(int [][] matrix, int div){
        matrix_ = matrix;
        div_ = div;
    }

    public int[][] add(int [][] matrix2){
        System.out.print('\n'+"Suma macierzy:"+'\n');
        int[][] matrix_tmp = new int [div_][div_];
        for(int i=0; i<div_; i++){
            for(int j=0; j<div_; j++){
                matrix_tmp[i][j] = matrix_[i][j] + matrix2[i][j];
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }

    public int[][] sub(int [][] matrix2){
        System.out.print('\n'+"Roznica macierzy:"+'\n');
        int[][] matrix_tmp = new int [div_][div_];
        for(int i=0; i<div_; i++){
            for(int j=0; j<div_; j++){
                matrix_tmp[i][j] = matrix_[i][j] - matrix2[i][j];
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }


    public int[][] mul(int [][] matrix2){
        System.out.print('\n'+"Iloczyn macierzy:"+'\n');
        int[][] matrix_tmp = new int [div_][div_];
        for(int i=0; i<div_; i++){
            for(int j=0; j<div_; j++){
                for(int k=0; k<div_; k++){
                    matrix_tmp[i][j] = matrix_tmp[i][j]+ matrix_[i][k] * matrix2[k][j];
                }
                System.out.print(matrix_tmp[i][j] + " ");
            }
            System.out.print('\n');
        }
        return matrix_tmp;
    }

}
