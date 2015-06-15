
/**
 *
 * @author vivek
 */

import javax.swing.JOptionPane;

public class avlWithRoot {
    




/**********************
  *************************
        @author::Vivek Mangla
**************************
*****************************/

/*******************************************************************

    Main.java is part of ADSLab.

     ADSLab is free code package: you can redistribute it    and/or modify
    it under the terms of the GNU General Public License as published     by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

   ADSLab is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with ADSLab.  If not, see <http://www.gnu.org/licenses/>.

*******************************************************************
*/



/**
 *
 * @author VIVEK_MANGLA
 */


//NOTE: UNCOMMENT /* */  TYPES OF COMMENT TO GET A FEEL OF WHAT AND WHEN  THERE IS A NEED FOR BALANCING aFTER dECLARATION OF Main Class




    static TreeV tmp2,nTS=null;//i.e. NodeToSwap=null
    static TreeV rootTREE=null;
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
insert(6);
insert(4);
insert(5);
insert(9);
insert(78);
insert(82);
insert(83);
insert(90);
insert(170);


insert(1);
/*insertTree(9,rootTREE);
insertTree(78,rootTREE);
insertTree(2,rootTREE);
insertTree(3,rootTREE);
insertTree(0,rootTREE);
insertTree(70,rootTREE);
insertTree(79,rootTREE);
insertTree(80,rootTREE);
//*/
print(rootTREE.left);
System.out.println();
print1(rootTREE.left);
System.out.println();
delete(90,rootTREE.left,rootTREE);
delete(4,rootTREE.left,rootTREE);
delete(6,rootTREE.left,rootTREE);
delete(1,rootTREE.left,rootTREE);
System.out.println("After Deletion");
print(rootTREE.left);
System.out.println();
print1(rootTREE.left);
    
    }


    static void print(TreeV root){

        if(root!=null){
                print(root.left);
System.out.print("  "+root.node);
        print(root.right);

            
        }

    }
    
    static void print1(TreeV root){

        if(root!=null){
                print1(root.left);
        print1(root.right);
System.out.print("  "+root.node);

            
        }

    }
    
    static void delete(int data,TreeV p,TreeV root){
        
        if(p!=null){
            if(p.node==data){
               goForDeletion(p,root);
            }
            else if(data<p.node){delete(data,p.left,p);}
            else delete(data,p.right,p);
            checkForUnbalancing(p,root);
        }
        else System.out.println("DATA "+data+"  NOT FOUND");
    }
    
    static void goForDeletion(TreeV p,TreeV root){
        
        
        if((p.left==null)&&(p.right==null)){
            if((p==root.left)&&(root==rootTREE)){
                rootTREE=null;
            }
            else {
                //In this case root might become unbalanced whose unbalancing might be checked **TWICE**.
                changeRootChild(root,p,null);
                
            }
        }
        else if(p.left==null){//As initially TreeV is Balanced , p's right subTree will contain at MAXIMUM only 1 node 
           changeRootChild(root,p,p.right);
           // swap(p,p.right);
            p.height=0;
            p.right=null;
            //Now p will be referencing to Alone Child => If It is Checked for Balancing Nothing will happen
            //BUT it's root which is now Pointing to it's previous right child WILL BE CHECKED FOR BALANCING....??
        }
        else if(p.right==null){//As initially TreeV is Balanced , p's left subTree will contain at MAXIMUM only 1 node
            changeRootChild(root,p,p.left);
            //swap(p,p.left);
            p.height=0;
            p.left=null;
        }
        else{
            if(p.left.right!=null)
            {
                nTS=null;
                findAndDelete(p.left,p);
                System.out.println("Attatching ="+nTS.node+" and Removing = "+p.node);
                changeRootChild(root,p,nTS);
                nTS.left=p.left;
                nTS.right=p.right;
                p.left=p.right=null;
                
                checkForUnbalancing(nTS,root);
                //swap(p,nTS);
            }
            else{
                
                p.left.right=p.right;
                changeRootChild(root,p,p.left);
                p.right=null;
                /*
                swap(p,p.left);
                if(p.left.left!=null){swap(p.left,p.left.left);p.left.left=null;}
                else p.left=null;
                */System.out.println("deleting"+ p.node);
                checkForUnbalancing(p.left,root);
                p.left=null;
            }
        }
        //checkForUnbalancing(root);
    }
    
    static void changeRootChild(TreeV root,TreeV p,TreeV toReplace){
        
                    if(root.left==p)root.left=toReplace;
                    else root.right=toReplace;
        
    }
    //Call this function only when during First Time Call,p.right exists.. 
    static void findAndDelete(TreeV p,TreeV root){
        
        if(p.right!=null){findAndDelete(p.right,p);}
        else{
            nTS=p;
            System.out.println("Inside findAndDelete and DETACHING = "+p.node);
            changeRootChild(root,p,p.left);
            p.left=null;
            
            /*
            nTS=new TreeV();
            nTS.node=p.node;
            if(p.left!=null){swap(p,p.left);p.left=null;}
            else{root.right=null;}*/
        }
        checkForUnbalancing(p,root);
        
    }
    
    

//Insertion in TreeV like in BST

    static void insert(int node){
        tmp2=new TreeV();
        tmp2.node=node;
        
       if(rootTREE==null){
           SIZE=1;
           rootTREE=new TreeV();
           //rootTREE.left will be referring to tmp2;
           rootTREE.left=tmp2;
       }
       else insertTree(rootTREE.left,rootTREE);
        
    }
    
    
    static void insertTree(TreeV p,TreeV root){
          if((tmp2.node<p.node)){
               if(p.left!=null)
               insertTree(p.left,p);
               else {
                   p.left=tmp2;
                   /*System.out.println("Inserted node is::"+node+" and root is"+p.node);*/
                   
               }
           }
           else{
                  if(p.right!=null)insertTree(p.right,p);
                  else {
                      p.right=tmp2;
                      /*System.out.println("Inserted node is::"+node+" and it's root is"+p.node);*/
                  }
           }
           //Check For Unbalancing of p whose root is root;
           //Here root is not to be checked otherwise rootTREE will be checked ??
           checkForUnbalancing(p,root);
      
    }


    static void checkForUnbalancing(TreeV p,TreeV root){
       //Compute current node's height..
       p.height=findHeight(p);
       //--calculate BalanceFactor--
       bf=l-r;
       /*System.out.println("bf for ROOTnode="+p.node+" is ::"+bf+" and HEIGHT before balancing is=::"+p.height);*/
       if(bf<0)bf*=-1;
       
      if(bf>1){
        BalanceTree(p,root);
      }
      
   }


    static void BalanceTree(TreeV root,TreeV ROOT){

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
                  doLL(root,ROOT);
               break;
           }
           case 2:{
               doRR(root,ROOT);
               break;
           }
           case 3:{
               doLR(root,ROOT);
               break;
           }
           case 4:{
               doRL(root,ROOT);
               break;
           }
           default:{System.out.println("CouldNOt Modify wC(whichCASE)");}
       }

   }

    static void doLL(TreeV root,TreeV ROOT){

    TreeV tmp;

    //-------Swapping root and root.left's datazzzz
   // int tmpint;
   /* swap(root,root.left);
    //----Doing what is required  i.e. updating ALL_POINTERS---

    tmp=root.left;
    root.left=tmp.left;
    tmp.left=tmp.right;
    tmp.right=root.right;
    root.right=tmp;

    //---done---
    //---UPDATING root and it's right child's heightss
    */
    tmp=root.left;
    changeRootChild(ROOT,root,tmp);
    root.left=tmp.right;
    tmp.right=root;
    
    root.height=findHeight(root);
    tmp.height=findHeight(tmp);
    
    
/*System.out.println("hieght of root ="+tmp.node+" after balancing is :: "+tmp.height);*/
}


    static void doRR(TreeV root,TreeV ROOT){

    TreeV tmp;

    //---Swapping root and root.right's datazzzz----

    //int tmpint;
   /* swap(root,root.right);
    //----Doing what is to be done i.e. updating ALL_POINTERS---

    tmp=root.right;
    root.right=tmp.right;
    tmp.right=tmp.left;
    tmp.left=root.left;
    root.left=tmp;
    */
    //--done--
    //---UPDATING root and it's right child's heightss
    tmp=root.right;
    changeRootChild(ROOT,root,tmp);
    root.right=tmp.left;
    tmp.left=root;
    
    root.height=findHeight(root);
    tmp.height=findHeight(tmp);
    


/*System.out.println("hieght of root ="+tmp.node+" after balancing is :: "+tmp.height);*/
}


    static void doLR(TreeV root,TreeV ROOT){

    doRR(root.left,root);
    doLL(root,ROOT);

}


    static void doRL(TreeV root,TreeV ROOT){
    doLL(root.right,root);
    doRR(root,ROOT);
}


    static int findHeight(TreeV node){
        //--find left and right height
    //reinitialize both to 0 first.
      l=r=0;   
    if(node.left!=null)l=1+node.left.height;
    if(node.right!=null)r=1+node.right.height;
    
    
    
    return ((l>r)?l:r);
}


}


class TreeV{
    int node=0,height=0;
    TreeV left=null,right=null;
  
}
