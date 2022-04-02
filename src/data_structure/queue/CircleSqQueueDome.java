package data_structure.queue;

import java.util.Scanner;

public class CircleSqQueueDome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CircleSqQueue circleSqQueue = new CircleSqQueue(4);


        boolean isExit=false;
        while(!isExit) {
            System.out.println("0:入列");
            System.out.println("1:出列");
            System.out.println("2:长度");
            System.out.println("3:查看队列");
            System.out.println("4:退出");
            System.out.println("请输入模式");
            int mode = scanner.nextInt();
            switch (mode) {
                case 0:
                    System.out.println("输入的元素为：");
                    Integer i = scanner.nextInt();
                    try {
                        circleSqQueue.offer(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        System.out.println("弹出元素为：" + circleSqQueue.poll());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("长度为：" + circleSqQueue.length());
                    break;
                case 3:
                    try {
                        circleSqQueue.showQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    isExit = true;
                    break;
            }
        }

    }

}
