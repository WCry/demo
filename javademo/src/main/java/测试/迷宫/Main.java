package 测试.迷宫;

import java.util.*;
public class Main{
    private static int n;
    private static int m;
    private static int[][] maze;
    private  class Node{
        int x;
        int y;
        Node(int x,int y){
            this.x=x;
            this.y=y;
        }
        public String toString(){
            return "("+x+","+y+")";
        }
    }
    private  boolean dfs(int[][] maze,int x,int y,Stack<Node> paths){
        if(maze[x][y]!=0) return false;
        paths.push(new Node(x,y));
        if(x==n-1&&y==m-1) return true;
        if(y+1<m&&dfs(maze,x,y+1,paths)){
            return true;
        }
        if(x+1<n&&dfs(maze,x+1,y,paths)){
            return true;
        }
        paths.pop();
        return false;
    }
    public  void solve(){
        Stack<Node> paths=new Stack();
        dfs(maze,0,0,paths);
        for(Node node:paths){
            System.out.println(node);
        }
    }
    public static void main(String[] arges){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            n=sc.nextInt();
            m=sc.nextInt();
            maze=new int[n][m];
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    maze[i][j]=sc.nextInt();
                }
            }
            Main so=new Main();
            so.solve();
        }
    }

}
