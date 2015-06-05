



import javax.swing.JOptionPane;


/**********************
  *************************
        @author::Vivek Mangla
**************************
*****************************/

/*******************************************************************

    Main.java is part of AVL_TREE.

    AVL_TREE is free code package: you can redistribute it    and/or modify
    it under the terms of the GNU General Public License as published     by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

   AVL_TREE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with AVL_TREE.  If not, see <http://www.gnu.org/licenses/>.

*******************************************************************
*/



/**
 *
 * @author VIVEK_MANGLA
 */




public class AVL_TREE {

    static Tree rootTREE=null,tmp2,nTS=null;//i.e. NodeToSwap=null
    static int SIZE=0;
    static int bf=0;
    static int sYNCHRO=0;
    static String answer;
    static int l=0,r=0,rootToSwap=0;

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
  /*       
insertTree(2,rootTREE);
insertTree(1,rootTREE);
insertTree(5,rootTREE);
insertTree(6,rootTREE);
insertTree(4,rootTREE);
insertTree(3,rootTREE);
    */    
insertTree(4,rootTREE);
insertTree(6,rootTREE);
insertTree(5,rootTREE);
insertTree(9,rootTREE);
insertTree(78,rootTREE);
insertTree(82,rootTREE);
insertTree(83,rootTREE);
insertTree(90,rootTREE);
insertTree(170,rootTREE);


insertTree(1,rootTREE);
/*insertTree(9,rootTREE);
insertTree(78,rootTREE);
insertTree(2,rootTREE);
insertTree(3,rootTREE);
insertTree(0,rootTREE);
insertTree(70,rootTREE);
insertTree(79,rootTREE);
insertTree(80,rootTREE);
//*/
print(rootTREE);
System.out.println();
print1(rootTREE);
System.out.println();
delete(1,rootTREE,rootTREE);
delete(4,rootTREE,rootTREE);
delete(9,rootTREE,rootTREE);
//delete(1,rootTREE,rootTREE);
System.out.println("After Deletion");
print(rootTREE);
System.out.println();
print1(rootTREE);
    }


    static void print(Tree root){

        if(root!=null){
                print(root.left);
System.out.print("  "+root.node);
        print(root.right);

            
        }

    }
    
    static void print1(Tree root){

        if(root!=null){
                print1(root.left);
        print1(root.right);
System.out.print("  "+root.node);

            
        }

    }
    
    static void delete(int data,Tree p,Tree root){
        
        if(p!=null){
            if(p.node==data){
               goForDeletion(p,root);
            }
            else if(data<p.node){delete(data,p.left,p);}
            else delete(data,p.right,p);
            checkForUnbalancing(p);
        }
        else System.out.println("DATA "+data+"  NOT FOUND");
    }
    
    static void goForDeletion(Tree p,Tree root){
        
        
        if((p.left==null)&&(p.right==null)){
            if((p==root)&&(p==rootTREE)){
                rootTREE=null;
            }
            else {
                //In this case root might become unbalanced whose unbalancing might be checked **TWICE**.
                changeRootChild(root,p,null);
                
            }
        }
        else if(p.left==null){//As initially Tree is Balanced , p's right subTree will contain at MAXIMUM only 1 node 
            //changeRootChild(root,p,p.right);
            swap(p,p.right);
            p.height=0;
            p.right=null;
        }
        else if(p.right==null){//As initially Tree is Balanced , p's left subTree will contain at MAXIMUM only 1 node
            //changeRootChild(root,p,p.left);
            swap(p,p.left);
            p.height=0;
            p.left=null;
        }
        else{
            if(p.left.right!=null)
            {
                nTS=null;
                findAndDelete(p.left,p);
                swap(p,nTS);
                /*Tree tmp=p.left;
                while(tmp.right.right!=null)tmp=tmp.right;
                p.node=tmp.right.node;
                delete(p.node,tmp.right,tmp);
                checkForUnbalancing(p);
                //p.node=rootToSwap;*/
            }
            else{
                swap(p,p.left);
                if(p.left.left!=null){swap(p.left,p.left.left);p.left.left=null;}
                else p.left=null;
                System.out.println("deleting"+ p.node);
                
                /*checkForUnbalancing(p);
                
                changeRootChild(root,p,p.left);
                p.left.right=p.right;
                p.right=p.left=null;*/
                //checkForUnbalancing(root.right);
                
                
            }
        }
        //checkForUnbalancing(root);
    }
    
    static void changeRootChild(Tree root,Tree p,Tree toReplace){
        
                    if(root.right==p)root.right=toReplace;
                    else root.left=toReplace;
        
    }
    //Call this function only when during First Time Call,p.right exists.. 
    static void findAndDelete(Tree p,Tree root){
        
        if(p.right!=null){findAndDelete(p.right,p);}
        else{
            nTS=new Tree();
            nTS.node=p.node;
            if(p.left!=null){swap(p,p.left);p.left=null;}
            else{root.right=null;}
        }
        checkForUnbalancing(p);
        
    }
    
    static void swap(Tree p1,Tree p2){
        int tmp;
        tmp=p1.node;
        p1.node=p2.node;
        p2.node=tmp;
    }
    

//Insertion in Tree like in BST

    static void insertTree(int node,Tree p){
     
       
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
       
           checkForUnbalancing(p);
           
           
           
       }
     
    }


    static void checkForUnbalancing(Tree p){
       //Compute current node's height..
       p.height=findHeight(p);
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
      if(answer.equals("y")){
          
          System.out.println(p.node+"  is UNBALANCED  p.r.h="+((p.right!=null)?p.right.height:-7)+" and p.r.r.h="+((p.right!=null)?((p.right.right!=null)?p.right.height:-90):-91));
          BalanceTree(p.node,p);
          /*print(rootTREE);*/
      }
     
          
      } 
   }


    static void BalanceTree(int data,Tree root){

       int wC;//FOR Determinimg which case is there

       if(root.right==null)                      //LL or LR unbalanced but no need to CHECK FURTHER.
       {
           if(root.left.left==null)//LR Case
           {wC=3;System.out.println("LR CASE1");}
           else wC=1;//LL Case
       }
       else if(root.left==null)                  //RR or RL unbalanced but no need to CHECK FURTHER.
       {
           if(root.right.right==null)//  RL case
           wC=4;
           else wC=2;//  RR  case 
       }
       else{
           if(root.left.height>root.right.height){
               if(root.left.right==null)wC=1;         //LL unbalanced
               else if(root.left.left==null){wC=3;System.out.println("LR Unb2");}     //LR unbalanced
               else{
                   if(root.left.left.height>=root.left.right.height)wC=1;   //LL unbalanced
                   else {wC=3;System.out.println("LR CASE3");}                         //LR unbalanced
               }
           }
           else{
               if(root.right.left==null)wC=2;          //RR unbalanced
               else if(root.right.right==null)wC=4;    //RL unbalanced
               else{
                   if(root.right.right.height>=root.right.left.height){wC=2;}  //RR unbalanced
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
   // int tmpint;
    swap(root,root.left);
    /*tmpint=root.node;
    root.node=root.left.node;
    root.left.node=tmpint;*/
    //----Doing what is required  i.e. updating ALL_POINTERS---

    tmp=root.left;
    root.left=tmp.left;
    tmp.left=tmp.right;
    tmp.right=root.right;
    root.right=tmp;

    //---done---
    //---UPDATING root and it's right child's heightss
    
    
    tmp.height=findHeight(tmp);
    root.height=findHeight(root);
    
    
/*System.out.println("hieght of root ="+tmp.node+" after balancing is :: "+tmp.height);*/
}


    static void doRR(Tree root){

    Tree tmp;

    //---Swapping root and root.right's datazzzz----

    //int tmpint;
    swap(root,root.right);/*
    tmpint=root.node;
    root.node=root.right.node;
    root.right.node=tmpint;*/
    //----Doing what is to be done i.e. updating ALL_POINTERS---

    tmp=root.right;
    root.right=tmp.right;
    tmp.right=tmp.left;
    tmp.left=root.left;
    root.left=tmp;
    
    //--done--
    //---UPDATING root and it's right child's heightss
    tmp.height=findHeight(tmp);
    root.height=findHeight(root);



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


    static int findHeight(Tree node){
        //--find left and right height
    //reinitialize both to 0 first.
      l=r=0;   
    if(node.left!=null)l=1+node.left.height;
    if(node.right!=null)r=1+node.right.height;
    
    
    
    return ((l>r)?l:r);
}


}

class Tree {

    int node=0,height=0;
    Tree left=null,right=null;

}
