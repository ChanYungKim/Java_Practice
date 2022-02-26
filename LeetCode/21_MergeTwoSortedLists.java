// [LeetCode] 21. Merge Two Sorted Lists

// Runtime: 1 ms, faster than 69.51% of Java online submissions for Merge Two Sorted Lists.
// Memory Usage: 43.6 MB, less than 5.82% of Java online submissions for Merge Two Sorted Lists.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    public void printValues (ListNode list1) {
        if (list1.next == null) {
            System.out.print (list1.val);
        }
        if (list1.next != null) { 
            System.out.print (list1.val);
            printValues (list1.next);
        }
    }
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode merged = null;
        
        if (list1 == null) {
            if (list2 == null) {
                return null;
            }
            return list2;
        }
        
        if (list2 == null) {
            if (list1 == null) {
                return null;
            }
            return list1;
        }
        
        if (list1.next == null) {
            if (list2.next == null) {
                if (list1.val <= list2.val) {
                    list1.next = list2;
                    return list1;
                }
                else {
                    list2.next = list1;
                    return list2;
                }
            }
            if (list1.val <= list2.val) {
                list1.next = list2;
                return list1;
            }
            else {
                list2.next = mergeTwoLists (list1, list2.next);
                return list2;
            }
        }
        
        if (list2.next == null) {
            if (list1.next == null) {
                if (list1.val <= list2.val) {
                    list1.next = list2;
                    return list1;
                }
                else {
                    list2.next = list1;
                    return list2;
                }
            }
            if (list1.val <= list2.val) {                
                list1.next = mergeTwoLists (list1.next, list2);
                return list1;
            }
            else {
                list2.next = list1;
                return list2;
            }
        }
        
        if (list1.next != null && list2.next != null) {
            if (list1.val <= list2.val) {
                merged = list1;
                list1.next = mergeTwoLists (list1.next, list2);
            }
            else {
                merged = list2;
                list2.next = mergeTwoLists (list1, list2.next);
            }
        }
        return merged;
    }
}
