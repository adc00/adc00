package com.yc.zp;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Author liuyachao123
 * @Date 2022/10/30 22:34
 * @Version 1.0
 */
//写一个同步容器粗来
public class SynchronizedCollections<T> {
    //写一个线程安全的同步容器  支持多个线程往里面装任务 多个线程往里面消费任务
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    //往容器中放任务 放元素 生产者线程
    public synchronized void put(T t) {
        while (lists.size() == MAX) {//如果这里用if的话 会造成问题
            //如果当前线程a放慢了然后被阻塞住  这个时候如果有别的线程唤醒了a  a
            //得到机会再运行的时候就不会再判断list.size==MAX,同一个线程用if的话只会判断一次
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
    }


    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;

    }

    public static void main(String[] args) {
        SynchronizedCollections<String> synchronizedCollections = new SynchronizedCollections<>();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                //每一个线程处理五个任务
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + "消费: " + synchronizedCollections.get());
//                    System.out.println(synchronizedCollections.get());

                }
            }, "consumer" + i).start();

        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                //每一个线程放25个
                for (int j = 0; j < 25; j++) {
                    synchronizedCollections.put(Thread.currentThread().getName() + "生产者" + j);
                }
            }, "producer" + i).start();

        }

    }

}
