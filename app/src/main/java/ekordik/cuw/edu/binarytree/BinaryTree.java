package ekordik.cuw.edu.binarytree;

/**
 * Created by emmakordik on 4/4/17.
 */

public class BinaryTree {
    private int payload;
    private BinaryTree leftTree;
    private  BinaryTree rightTree;

    public BinaryTree() {
        leftTree = null;
        rightTree = null;
    }

    public BinaryTree(final int payload){
        this.payload = payload;
        this.leftTree = null;
        this.rightTree = null;
    }

    public void add(int payload) {
        if(payload > this.payload){
            if(rightTree == null) {
                this.rightTree = new BinaryTree(payload);
            }else {
                rightTree.add(payload);
            }
        }else if(payload <= this.payload) {
            if(leftTree == null) {
                this.leftTree = new BinaryTree(payload);
            }else {
                leftTree.add(payload);
            }
        }else{
            this.payload = payload;
        }
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    public BinaryTree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(BinaryTree leftTree) {
        this.leftTree = leftTree;
    }

    public BinaryTree getRightTree() {
        return rightTree;
    }

    public void setRightTree(BinaryTree rightTree) {
        this.rightTree = rightTree;
    }

}
