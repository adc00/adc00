package com.yc.zp.caculate;

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

        while (result%10!=0) {



        }
        return result;

    }

    public static void main(String[] args) {
        int result = 635;//反转之后是536


    }

}
