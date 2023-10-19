package edu.baylor.ecs.csi3471.AnastasiWesley.module3;

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.testAdd();
        System.out.println("-----------------");
        main.testSubtract();
        System.out.println("-----------------");
        main.testMultiply();
    }
    public void testAdd() throws Exception {
        Matrix matrix1 = new Matrix(5,5);
        Matrix matrix2 = new Matrix(5,5);
        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println();
        System.out.println("Matrix 2:");
        matrix2.print();
        System.out.println("testing add:\n");
        matrix1.add(matrix2);
        matrix1.print();
    }
    public void testSubtract() throws Exception {
        Matrix matrix1 = new Matrix(5,5);
        Matrix matrix2 = new Matrix(5,5);
        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println();
        System.out.println("Matrix 2:");
        matrix2.print();
        System.out.println("testing Subtract:\n");
        matrix1.subtract(matrix2);
        matrix1.print();
    }
    public void testMultiply() throws Exception {
        Matrix matrix1 = new Matrix(3,2);
        Matrix matrix2 = new Matrix(6,3);
        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println();
        System.out.println("Matrix 2:");
        matrix2.print();
        System.out.println("testing multiply:\n");
        matrix1.multiply(matrix2);
        matrix1.print();
    }
}
