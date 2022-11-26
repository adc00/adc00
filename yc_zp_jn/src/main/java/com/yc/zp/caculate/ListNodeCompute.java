package com.yc.zp.caculate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author liuyachao123
 * @Date 2022/11/1 20:51
 * @Version 1.0
 */
public class ListNodeCompute {
    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    private Boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;
    }

    /**
     * 获取单链表的中间节点
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //这个时候慢指针走到中点
        return slow;

    }

    /**
     * 使用递归操作反转整个链表
     *
     * @param head
     * @return
     */
    public ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseListNode(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public int reverseNumber(int a) {
        int result = 0;

        while (result % 10 != 0) {


        }
        return result;

    }

    public static void main(String[] args) {
        int result2 = 635;//反转之后是536
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list1.add(i);
            list2.add(i * 2);
        }
        List<Integer> result = Stream.of(list1, list2).flatMap(List::stream).filter(l -> l.intValue() > 3).distinct().collect(Collectors.toList());
        result.forEach(l -> System.out.println(l));
        result = Stream.of(list1, list2).flatMap(List::stream).distinct().collect(Collectors.toList());
        result.forEach(l -> System.out.println(l));

        List<String> list3 = new ArrayList<>();
        list3.add("lyc1");
        list3.add("xx2");
        list3.add("");
        list3.add("LYC1");
        list3.forEach(l -> System.out.println(l));
        System.err.println("end");
        String testStr = "LYC1";
//        list3.clear();
        List<String> testStringResult = list3.stream().filter(l -> Optional.ofNullable(l)
                .orElse(Collections.emptyList().toString()).equalsIgnoreCase(testStr)).collect(Collectors.toList());
        testStringResult.forEach(l -> System.out.println(l));


    }

}
