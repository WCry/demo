package slowfast;

import java.util.Objects;

public class BackWordKNode {
    public static void main(String[] args) {
         LinkNode linkNode=new LinkNode(1);
         LinkNode linkNode1=new LinkNode(2);
         LinkNode linkNode2=new LinkNode(3);
         LinkNode linkNode3=new LinkNode(4);
         LinkNode linkNode4=new LinkNode(5);
         linkNode.setNext(linkNode1);
         linkNode1.setNext(linkNode2);
         linkNode2.setNext(linkNode3);
         linkNode3.setNext(linkNode4);
         LinkNode slowNode=linkNode;
         LinkNode fastNode=linkNode.getNext().getNext();
         while (!Objects.isNull(fastNode.getNext())){
              slowNode=slowNode.getNext();
              fastNode=fastNode.getNext();
         }
         System.out.println(slowNode.getData());
    }
}
