package edu.baylor.ecs.csi3471.AnastasiWesley.module4;

public class Loop {
    public static void main(String[] args)
    {
        Loop loop = new Loop();
        loop.loop(10);
    }
    public void loop(int count)
    {
        count--;
        if (count > 0) {
            System.out.println(count);
            loop(count);
        }
    }
}
