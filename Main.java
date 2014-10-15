

import javax.swing.JOptionPane;



/**
 *
 * @author VIVEK_MANGLA
 */


//NOTE: UNCOMMENT /* */  TYPES OF COMMENT TO GET A FEEL OF WHAT AND WHEN  THERE IS A NEED FOR BALANCING aFTER dECLARATION OF Main Class



public class Main {

    static Tree rootTREE=null,tmp2;
    static int SIZE=0;
    static int bf=0;
    static int sYNCHRO=0;
    static String answer;

    public static void main(String[] arg){

/*
 This Code can be modified by commenting  UNCOMMENTED code in main(arg) method and UNCOMMENTING BELOW CODE
 */

        /*
         static Scanner s;

         * System.out.println("Enter no of nodes");
         * int n=s.nextInt();
         * for(int i=0;i<n;i++){
         * insertTree(s.nextInt(),root);checkBalancedFactor();
         }
         print(root);

         */



insertTree(1,rootTREE);
insertTree(9,rootTREE);
insertTree(78,rootTREE);
insertTree(2,rootTREE);
insertTree(3,rootTREE);
insertTree(0,rootTREE);
insertTree(70,rootTREE);
insertTree(79,rootTREE);
insertTree(80,rootTREE);

print(rootTREE);


    }


    static void print(Tree root){

        if(root!=null){
            System.out.println(root.node);
        print(root.left);
        
        print(root.right);

        }

    }

//Insertion in Tree like in BST

   static void insertTree(int node,Tree p){
       int l=0,r=0;
       
       if(rootTREE==null){
           SIZE=1;
           rootTREE=new Tree();
           rootTREE.node=node;
       }
       else{
          

           if((node<p.node)){
               if(p.left!=null)
               insertTree(node,p.left);
               else {
                   tmp2=new Tree();
                   tmp2.node=node;
                   p.left=tmp2;
                   /*System.out.println("Inserted node is::"+node+" and root is"+p.node);*/
                   
               }
           }
           else{
                  if(p.right!=null)insertTree(node,p.right);
                  else {
                      tmp2=new Tree();
                      tmp2.node=node;
                      p.right=tmp2;
                      /*System.out.println("Inserted node is::"+node+" and it's root is"+p.node);*/
                  }
              
           }
        
           //--find left and right height
       if(p.left!=null)l=1+p.left.height;//else l=0;
       if(p.right!=null)r=1+p.right.height;//else r=0;
       //---COMPUTE current node's height---
       if((l!=0)||(r!=0)){p.height=((l>r)?l:r);}
       //--calculate BalanceFactor--
       bf=l-r;
       /*System.out.println("bf for ROOTnode="+p.node+" is ::"+bf+" and HEIGHT before balancing is=::"+p.height);*/
       if(bf<0)bf*=-1;
       
      if(bf>1){sYNCHRO=1;
      //--Taking User Choice for Balancing
      try{
      answer=JOptionPane.showInputDialog("Do You Want to Balance it as node with data="+p.node+" is UNBALANCED::y for yes n for no");
      }catch(Exception er){answer="n";}
      if(answer==null)answer="n";
      if(answer.equals("y")){BalanceTree(p.node,p,l,r);/*print(rootTREE);*/}
     
          
      }
       }
     
    }




   static void BalanceTree(int data,Tree root,int leftHeight,int rightHeight){

       int wC;//FOR Determinimg which case is there

       if(root.right==null)wC=1;                      //LL unbalanced
       else if(root.left==null)wC=2;                  //RR unbalanced
       else{
           if(root.left.height>root.right.height){
               if(root.left.right==null)wC=1;         //LL unbalanced
               else if(root.left.left==null)wC=3;     //LR unbalanced
               else{
                   if(root.left.left.height>root.left.right.height)wC=1;   //LL unbalanced
                   else wC=3;                         //LR unbalanced
               }
           }
           else{
               if(root.right.left==null)wC=2;          //RR unbalanced
               else if(root.right.right==null)wC=4;    //RL unbalanced
               else{
                   if(root.right.right.height>root.right.left.height)wC=2;  //RR unbalanced
                   else wC=4;                                               //RL unbalanced
               }
           }

       }


       switch(wC){
           case 1:{
                  doLL(root);
               break;
           }
           case 2:{
               doRR(root);
               break;
           }
           case 3:{
               doLR(root);
               break;
           }
           case 4:{
               doRL(root);
               break;
           }
           default:{System.out.println("CouldNOt Modify wC(whichCASE)");}
       }

   }

static void doLL(Tree root){

    Tree tmp;

    //-------Swapping root and root.left's datazzzz
    int tmpint;
    tmpint=root.node;
    root.node=root.left.node;
    root.left.node=tmpint;
    //----Doing what is required  i.e. updating ALL_POINTERS---

    tmp=root.left;
    root.left=tmp.left;
    tmp.left=tmp.right;
    tmp.right=root.right;
    root.right=tmp;

    //---done---
    //---UPDATING root and it's right child's heightss
    root.height-=1;
    tmp.height=root.height-1;

/*System.out.println("hieght of root ="+tmp.node+" after balancing is :: "+tmp.height);*/
}


static void doRR(Tree root){

    Tree tmp;

    //---Swapping root and root.right's datazzzz----

    int tmpint;

    tmpint=root.node;
    root.node=root.right.node;
    root.right.node=tmpint;
    //----Doing what is to be done i.e. updating ALL_POINTERS---

    tmp=root.right;
    root.right=tmp.right;
    tmp.right=tmp.left;
    tmp.left=root.left;
    root.left=tmp;
    
    //--done--
    //---UPDATING root and it's right child's heightss
root.height=root.height-1;
tmp.height=root.height-1;
/*System.out.println("hieght of root ="+tmp.node+" after balancing is :: "+tmp.height);*/
}


static void doLR(Tree root){

    doRR(root.left);
    doLL(root);

}


static void doRL(Tree root){
    doLL(root.right);
    doRR(root);
}





}

class Tree {

    int node=0,height=0;
    Tree left=null,right=null;

}
