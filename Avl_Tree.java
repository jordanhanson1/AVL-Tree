


import java.util.ArrayList;
import java.util.List;

public class Avl_Tree {


    //get height of a node
    public int getHeight(Node nod){
        if (nod==null) return 0;
        return nod.height;
    }

    //set height of a node
    public void setHeight(Node nod){
        if (nod==null) return;
        nod.height=1+Math.max(getHeight(nod.right),getHeight(nod.left));
    }

    //pre order traversal
    public void preOrder(Node current){
        if (current==null) return;
        preOrder(current.left);
        System.out.println(current.key);
        preOrder(current.right);
    }

    //level order traversal
    public List<List<Integer>> levelOrder(){
        if (root==null) return new ArrayList<>();
        List<List<Integer>> levels=new ArrayList<>();
        List<Node> currentNodes=new ArrayList<>();
        List<Integer> currentVals=new ArrayList<>();
        List<Node> nextNodes=new ArrayList<>();
        currentNodes.add(root);
        while (!currentNodes.isEmpty()){
            Node curr=currentNodes.remove(0);
            currentVals.add(curr.key);
            if (curr.left != null)
                nextNodes.add(curr.left);
            if (curr.right != null)
                nextNodes.add(curr.right);

            if (currentNodes.isEmpty()){
                levels.add(currentVals);
                currentVals=new ArrayList<>();
                currentNodes=nextNodes;
                nextNodes=new ArrayList<>();
            }
        }
        return levels;
    }

    

    //root node
    public Node root;

    //initialize tree
    public Avl_Tree(){
        this.root=null;
    }

    //insert
    public void insert(int key){
        this.root=insertRecursion(this.root,key);
    }

    //insert key
    private Node insertRecursion(Node current,int key){


        //empty tree case
        if (current == null){
            return new Node(key);
        }

        //left if smaller and right if bigger
        if (key>current.key){
            current.right=insertRecursion(current.right,key);
        }
        else{
            current.left=insertRecursion(current.left,key);
        }

        //set height
        setHeight(current);
        //balance tree at current node
        current=balance(current);


        return current;

    }

    //balance
    private Node balance(Node current){
        if  (current==null) return current;


        //left and right height
        int leftHeight=getHeight(current.left);
        int rightHeight=getHeight(current.right);

        //left side bigger than right side
        if (leftHeight>rightHeight+1){

            int leftLeft=getHeight(current.left.left);
            int leftRight=getHeight(current.left.right);



            //right rotate
            if (leftLeft>=leftRight){
                current=rRotate(current);
            }
            //left right
            else{

                current=lrRotate(current);
            }

        }
        //right side bigger than left side
        if (rightHeight>leftHeight+1){
            int rightRight=getHeight(current.right.right);
            int rightLeft=getHeight(current.right.left);

            if (rightRight>=rightLeft){

                current=lRotate(current);
            }
            else{

                current=rlRotate(current);
            }
        }


        return current;
    }

    //rotations
    //right rotate
    public Node rRotate(Node root){
        Node nextRoot=root.left;
        root.left=nextRoot.right;
        nextRoot.right=root;
        setHeight(root);
        setHeight(nextRoot);
        return nextRoot;
    }
    //left rotate
    public Node lRotate(Node root){
        Node nextRoot=root.right;
        root.right=nextRoot.left;
        nextRoot.left=root;
        setHeight(root);
        setHeight(nextRoot);
        return nextRoot;
    }
    //left right rotate
    public Node lrRotate(Node root){
        root.left=lRotate(root.left);
        root=rRotate(root);
        return root;
    }
    //right left rotate
    public Node rlRotate(Node root){
        root.right=rRotate(root.right);
        root=lRotate(root);
        return root;
    }


    //search for one key if none then return null
    public Integer search(int key){
        Node current=this.root;
        while (current != null){
            if (current.key==key) {
                return current.key;
            }
            else if (current.key<key){
                current=current.right;
            }
            else{
                current=current.left;
            }
        }
        return null;
    }

    //search a range of keys
    public List<Integer> search(int key1, int key2){
        List<Integer> keys=new ArrayList<>();
       searchRange(this.root,key1,key2,keys);
       return keys;
    }
    //search helper
    private void searchRange(Node root,int key1, int key2, List<Integer> keys){
        if (root==null) return;

        if (root.key>=key1){
            searchRange(root.left,key1,key2,keys);
        }

        if (key1<=root.key && root.key<=key2){
            keys.add(root.key);
        }

        if (key2>=root.key){
            searchRange(root.right,key1,key2,keys);
        }

    }

    //get biggest node in tree used for delete
    private Node biggest(Node current){
        if (current == null) return current;

        while (current.right != null){
            current=current.right;
        }
        return current;
    }
    //delete
    public void delete(int key){
        root=deleteNode(root,key);
    }

    //delete helper
    private Node deleteNode(Node current, int val){
        //base case
        if (current==null)
            return null;

        //search ops
        if (current.key>val){
            current.left=deleteNode(current.left,val);
        }
        else if (current.key<val){
            current.right=deleteNode(current.right,val);
        }
        //matches so delete it
        else{
            //two children
            if (current.right!=null && current.left!=null){

                //largest in left subtree
                Node largestLeft=biggest(current.left);
                current.key=largestLeft.key;
                current.left=deleteNode(current.left,largestLeft.key);

            }
            //no left child
            else if (current.right!=null){
                current=current.right;
            }
            //no right child
            else if (current.left != null){
                current=current.left;
            }
            //no children safe delete
            else{
                return null;
            }

        }

        //balance new tree
        setHeight(current);
        current=balance(current);
        return current;
    }
}


