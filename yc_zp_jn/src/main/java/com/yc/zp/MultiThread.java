package com.yc.zp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author liuyachao123
 * @Date 2022/8/12 10:27
 * @Version 1.0
 */
public class MultiThread {
    private static Integer num = 3;
    private static CountDownLatch countDownLatch = new CountDownLatch(num);

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(num, () -> {

        System.out.println("所有人都好了，开始开会!");
        System.out.println("--------------------");

    });
    private static ExecutorService executorService = Executors.newFixedThreadPool(num);

    public static void main(String[] args) throws InterruptedException {
        executorService.submit(() -> {
            System.out.println("A开始上厕所");
            try {
                //这个时候开始做什么呢
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                countDownLatch.countDown();//等countdown为0时执行后面的操作
                System.out.println("A上完了");
            }

        });

        executorService.submit(() -> {
            System.out.println("B开始上厕所");
            try {
                //这个时候开始做什么呢
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("B上完了");
            }

        });
        executorService.submit(() -> {
            System.out.println("C开始上厕所");
            try {
                //这个时候开始做什么呢
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
                System.out.println("C上完了");
            }

        });

        System.out.println("等待所有人从厕所回来开会");
        countDownLatch.await();//所有线程都完成了才开始执行下面的这段
        System.out.println("所有人都好了，开始开会");

        executorService.shutdown();
    }

//    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//
//
//        executorService.submit(() -> {
//            System.out.println("A在上厕所");
//            try {
//                Thread.sleep(1000);
//                System.out.println("A上完了");//这里说的达到某个状态指的是，都达到await边界这里
//                cyclicBarrier.await();//执行完cyclicbralier内容后开始执行这个
//                System.out.println("会议结束，A退出");
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//        });
//        executorService.submit(() -> {
//            System.out.println("B在上厕所");
//            try {
//                Thread.sleep(2000);
//                System.out.println("B上完了");
//                cyclicBarrier.await();
//                System.out.println("会议结束，B退出");
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//
//        });
//        executorService.submit(() -> {
//            System.out.println("C在上厕所");
//            try {
//                Thread.sleep(3000);
//                System.out.println("C上完了");
//                cyclicBarrier.await();
//                System.out.println("会议结束，C退出");
//            } catch (InterruptedException | BrokenBarrierException e) {
//                e.printStackTrace();
//            }
//
//        });
//
//         executorService.shutdown();
//    }

//    private static Integer limitThread = 3;//2;//1; //0;//最多只能允许limitThread个线程同时运行 做限流使用
//    private static Semaphore semaphore = new Semaphore(limitThread);
//
//    public static void main(String[] args) throws InterruptedException {
//        //测试心好累 信号量 2022年8月12日 19点53分 浦东周浦医院 从8.3开始住院
//        //对于多线程来说 是没有标准答案的 只要大体上是对的
//
////        executorService.submit(() -> {
////            try {
////                semaphore.acquire();
////                System.out.println("A开始上厕所");
////                semaphore.release();
////                System.out.println("A结束上厕所");
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            //            try {
//////                Thread.sleep(1000);
////
//////            semaphore.release();
//////            System.out.println("A结束上厕所");
//////            } catch (InterruptedException e) {
//////                e.printStackTrace();
//////            }
////
////
////        });
////
////        executorService.submit(() -> {
////            try {
////                semaphore.acquire();
////                System.out.println("B开始上厕所");
////                semaphore.release();
////                System.out.println("B结束上厕所");
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            //            try {
//////                Thread.sleep(2000);
//////            semaphore.release();
//////            System.out.println("B结束上厕所");
//////            } catch (InterruptedException e) {
//////                e.printStackTrace();
//////            }
////
////
////        });
////
////        executorService.submit(() -> {
////            try {
////                semaphore.acquire();
////                System.out.println("C开始上厕所");
////                semaphore.release();
////                System.out.println("C结束上厕所");
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            //            try {
//////                Thread.sleep(3000);
//////            semaphore.release();
//////            System.out.println("C结束上厕所");
//////            } catch (InterruptedException e) {
//////                e.printStackTrace();
//////            }
////
////
////        });
////        System.out.println("等所有人从厕所回来开会");
////        semaphore.acquire(num);
////        semaphore.release();
////        semaphore.release();
////        semaphore.release();
////        System.out.println("所有人都好了，开始开会");
////        executorService.shutdown();
////
////        for (int index = 0; index < 10; index++) {
////            final int No = index;
////            Runnable ru = new Runnable() {
////                @Override
////                public void run() {
////                    try {
////                        //获取许可
////                        semaphore.acquire();
////                        System.out.println("accessing:" + No);
////                        Thread.sleep((long) Math.random() * 1000);
////                        System.out.println("release:" + No);
////                        semaphore.release();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////
////                }
////            };
//////            executorService.execute(ru);
////            executorService.submit(ru);
////
////        }
//
//
//    }



}
