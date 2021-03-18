package letCode.letCode;

public class TestReverseK {
    public static void main(String[] args) {
        ListNode listNode1=new ListNode();
        listNode1.val=1;
        ListNode listNode2=new ListNode();
        listNode2.val=2;
        ListNode listNode3=new ListNode();
        listNode3.val=3;
        ListNode listNode4=new ListNode();
        listNode4.val=4;
        ListNode listNode5=new ListNode();
        listNode5.val=5;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        ListNode listNode= reverseKGroup(listNode1,2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }
    public static ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        if(k<=1){
            return head;
        }
        int temple=0;
        ListNode[] re=new ListNode[k];
        ListNode newHead=null;
        ListNode current=head;
        ListNode templeNode;
        ListNode preNode=null;
        while(current!=null){
            if(temple<=k-1){
                re[temple]=current;
                if(temple==k-1){
                    if(newHead==null){
                        newHead=current;
                    }
                    //将后一个记录下来
                    templeNode=current.next;
                    //进行翻转
                    for(int i=k-1;i>0;i--){
                        re[i].next=re[i-1];
                    }
                    re[0].next=templeNode;
                    if(preNode!=null){
                        preNode.next=re[k-1];
                    }else{
                        preNode=re[0];
                    }
                    temple=0;
                    re=new ListNode[k];
                    current=templeNode;
                }else{
                    temple++;
                    current=current.next;
                }
            }
        }
        return newHead;
    }
}
