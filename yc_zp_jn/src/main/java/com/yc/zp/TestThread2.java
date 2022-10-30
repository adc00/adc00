package com.yc.zp;

/**
 * @Author liuyachao123
 * @Date 2022/8/31 11:02
 * @Version 1.0
 */
public class TestThread2 {

    //线程交替打印
//    private volatile Boolean turn = false;
    private Boolean turn = false;//这个加不加都无所谓

    private synchronized void printA() {

        if (!turn) {
            System.out.println("当前线程0 ：{}" + Thread.currentThread().getName());
//            System.out.println(Thread.currentThread().getName() + i);
            try {
                turn = true;
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        turn = false;
//        System.out.println("当前线程0 ：状态{}"+turn);
//        this.notify();
    }


    private synchronized void printB() {
        if (turn) {
            System.out.println("当前线程1 ：{}" + Thread.currentThread().getName());
//            System.out.println(Thread.currentThread().getName() + i);
            try {
                turn = false;
                this.notify();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        turn = true;
//        System.out.println("当前线程1 ：状态{}"+turn);


    }


    public static void main(String[] args) {

        TestThread2 testThread2 = new TestThread2();
        System.out.println(123123);
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    testThread2.printA();
//                }
//            }
//        };

//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    testThread2.printB();
//                }
//            }
//        };
//        thread1.start();
//        thread2.start();
//        AtomicInteger i = new AtomicInteger(0);
//        AtomicInteger j = new AtomicInteger(0);

        new Thread() {
            @Override
            public void run() {
//                for (; i.get() < 100; i.incrementAndGet()) {
//                    testThread2.printA();
//                }

                for (int i = 0; i < 100; i++) {
                    testThread2.printA();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
//                for (; j.get() < 100; j.incrementAndGet()) {
//                    testThread2.printB();
//                }
                for (int j = 0; j < 100; j++) {
                    testThread2.printB();
                }

            }
        }.start();


    }

}
