package CSI_3471.Lab1;

/* This is a simple Java program.
   FileName : "HelloWorld.java". */
public class HelloWorld {
    // Your program begins with a call to main().
    // Prints "Hello, World" to the terminal window.
    public static void main(String args[]) {
        System.out.println("Hello, World");
        int a = 1;
        while (a < 5) {
            a++;
            if (a == 3) {
               System.out.println("Jackpot!");
            }
        }
    }
}