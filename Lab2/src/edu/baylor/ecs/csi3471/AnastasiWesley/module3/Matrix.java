package edu.baylor.ecs.csi3471.AnastasiWesley.module3;

public class Matrix {
    int rows;
    int columns;
    int[][] matrix;
    Matrix(int x, int y)
    {
        this.columns = x;
        this.rows = y;
        this.matrix = new int[this.columns][this.rows];
        for (int i = 0; i < this.columns; i++)
        {
            for (int j = 0; j < this.rows; j++)
            {
                this.matrix[i][j] = (int) (Math.random()*20);
            }
        }
    }
    public void add(Matrix input) throws Exception {
        if (this.columns != input.columns || this.rows != input.rows)
        {
            throw new Exception("Incompatible dimensions");
        }

        for (int i = 0; i < this.columns; i++)
        {
            for (int j = 0; j < this.rows; j++)
            {
                this.matrix[i][j] += input.matrix[i][j];
            }
        }
    }
    public void subtract(Matrix input) throws Exception {
        if (this.columns != input.columns || this.rows != input.rows)
        {
            throw new Exception("Incompatible dimensions");
        }

        for (int i = 0; i < this.columns; i++)
        {
            for (int j = 0; j < this.rows; j++)
            {
                this.matrix[i][j] -= input.matrix[i][j];
            }
        }
    }
    public void multiply(Matrix input) throws Exception {
        if (this.columns != input.rows)
        {
            throw new Exception("Incompatible dimensions");
        }

        Matrix result = new Matrix(input.columns, this.rows);
        int columnMult;

        for (int s = 0; s < this.rows; s++) {
            for (int j = 0; j < input.columns; j++) {
                columnMult = 0;
                for (int i = 0; i < this.columns; i++) {
                    columnMult += this.matrix[i][s] * input.matrix[j][i];
                }
                result.matrix[j][s] = columnMult;
            }
        }
        this.columns = result.columns;
        this.rows = result.rows;
        this.matrix = result.matrix;
    }
    public void print()
    {
        for (int i = 0; i < this.rows; i++)
        {
            System.out.print("[ ");
            for (int j = 0; j < this.columns; j++)
            {
                if (j != this.columns-1) {
                    System.out.print(this.matrix[j][i] + ", ");
                }
                else
                {
                    System.out.println(this.matrix[j][i] + " ]");
                }
            }
        }
    }
}
