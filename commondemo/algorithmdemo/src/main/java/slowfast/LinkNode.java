package slowfast;


import lombok.Getter;
import lombok.Setter;

/**
 * user:zxp
 * Day:2020,02,14
 **/
public class LinkNode {
    @Getter
    private int data;
    @Getter
    @Setter
    private LinkNode next;

    public LinkNode(int i) {
        data=i;
    }
    public  LinkNode(int i,LinkNode nextNode){
        data=i;
        next=nextNode;
    }
}
