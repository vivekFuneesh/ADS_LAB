

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Main {

static Scanner s;
static int num=0;
static Node root=null,tmp=null;
    
    public static void main(String[] arg){

       s=new Scanner(System.in);
       System.out.println("Enter NO._OF_NODES to be inserted");
       num=s.nextInt();
       int data=0;
       for(int i=0;i<num;i++){

           System.out.println("\nEnter data to be inserted");
           data=s.nextInt();
           insert_Tree(root,data);
           System.out.println("NEW_TREE_IS::");
           Node traverse=root;
           while(traverse!=null){
           printTree(traverse);
           System.out.println();
           traverse=traverse.nextSibling;
           }
       }

    }


    public static void printTree(Node p){
        if(root==null){}
        else if(p!=null){
            System.out.print(" "+p.data);
            if(p.child!=null){
                for(int i=0;i<p.child.length;i++){
                    printTree(p.child[i]);
                }
            }
            
        }
    }



    //@SuppressWarnings("exporting")
    public static void insert_Tree(Node p,int item){

        if(root==null){
            root=new Node();root.data=item;
        }
        else{
            tmp=new Node();tmp.data=item;
            tmp.nextSibling=root;
            root=tmp;

            while((root!=null)&&(root.nextSibling!=null)){
                if(root.degree==root.nextSibling.degree)
                Merge(root,root.nextSibling);
                else break;
            }
        }


    }

    public static void Merge(Node p1,Node p2){

        Node min=(p2.data>p1.data)?p1:p2;
        Node max=(p2.data<p1.data)?p1:p2;
        root=p2.nextSibling;
        if(min.child==null){
            min.child=new Node[1];
            min.child[0]=max;
        }
        else{
            Node[] tmpArr=new Node[min.child.length+1];
            tmpArr[0]=max;
            for(int i=0;i<min.child.length;i++){
                tmpArr[i+1]=min.child[i];
            }
            min.child=null;
            min.child=tmpArr;
            tmpArr=null;

            System.out.println("ALL childs of root="+min.data+ "are::");
            for(int i=0;i<min.child.length;i++){
                System.out.print(" "+min.child[i].data);
            }
            System.out.println("-------------------------------------------------");
        }

        min.degree+=1;
        min.nextSibling=root;
        root=min;
        

    }






    



}



class Node{

    int data=0,degree=0;
    Node nextSibling=null;
    Node[] child=null;
}