package ekordik.cuw.edu.binarytree;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by emmakordik on 4/4/17.
 */

public class BinaryTree {
    private int payload;
    private BinaryTree leftTree;
    private  BinaryTree rightTree;
    private int depth;
    private Position position; //Position relative to its parent
    private BinaryTree parent;

    public BinaryTree() {
        leftTree = null;
        rightTree = null;
    }

    public BinaryTree(final int payload){
        this.payload = payload;
        this.leftTree = null;
        this.rightTree = null;
        this.depth = 0;
        this.position = Position.ROOT;
        this.parent = null;
    }

    public BinaryTree(final int payload, final int depth, final Position position, final BinaryTree parent) {
        this(payload);
        this.depth = depth;
        this.position = position;
        this.parent = parent;
    }

    public void add(int payload) {
        if(payload > this.payload){
            if(rightTree == null) {
                this.rightTree = new BinaryTree(payload, this.depth+1, Position.LEFT_CHILD, this);
            }else {
                rightTree.add(payload);
            }
        }else if(payload <= this.payload) {
            if(leftTree == null) {
                this.leftTree = new BinaryTree(payload, this.depth+1, Position.RIGHT_CHILD, this);
            }else {
                leftTree.add(payload);
            }
        }else{
            this.payload = payload;
        }
    }

    public int getDepth() {
        int leftDepth = 0;
        int rightDepth = 0;

        if(this.leftTree != null){
            leftDepth = this.leftTree.getDepth()+1;
        }

        if(this.rightTree != null){
            rightDepth = this.rightTree.getDepth() +1;
        }

        return leftDepth >= rightDepth ? leftDepth : rightDepth;
    }


    public int getBalanceFactor() {
        int leftDepth = this.leftTree != null ? this.leftTree.getDepth() : 0;
        int rightDepth = this.rightTree != null ? this.rightTree.getDepth() : 0;

        return leftDepth - rightDepth;
    }

    public TreeState getBalance() {
        int leftDepth = this.leftTree != null ? this.leftTree.getDepth() : 0;
        int rightDepth = this.rightTree != null ? this.rightTree.getDepth() : 0;

        int difference = Math.abs(this.getBalanceFactor());
        if(difference <= 1) {
            return TreeState.BALANCED;
        }else if(leftDepth > rightDepth) {
            return TreeState.LEFT;
        }else {
            return TreeState.RIGHT;
        }
    }

    public void displayInOrder(BinaryTree tree, ViewGroup view, Context context){
        if(tree == null) {
            return;
        }

        displayInOrder(tree.getLeftTree(), view, context);
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setText(tree.toLexicalJSON());
        view.addView(tv);
        displayInOrder(tree.getRightTree(), view, context);
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

    public BinaryTree getRightTree() {
        return rightTree;
    }

    public BinaryTree getParent() {
        return parent;
    }

    public String toLexicalJSON(){
        return "{\"depth\": \"" + this.depth + "\", \"position\":\"" + this.position + "\", \"payload\":" + this.payload + "}";
    }
}
