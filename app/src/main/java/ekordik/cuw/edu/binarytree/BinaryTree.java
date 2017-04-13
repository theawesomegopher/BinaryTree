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
    private char position;

    public BinaryTree() {
        leftTree = null;
        rightTree = null;
    }

    public BinaryTree(final int payload){
        this.payload = payload;
        this.leftTree = null;
        this.rightTree = null;
        this.depth = 0;
        this.position = 'N';
    }

    public BinaryTree(final int payload, final int depth, final char position) {
        this(payload);
        this.depth = depth;
        this.position = position;
    }

    public void add(int payload) {
        if(payload > this.payload){
            if(rightTree == null) {
                this.rightTree = new BinaryTree(payload, this.depth+1, 'L');
            }else {
                rightTree.add(payload);
            }
        }else if(payload <= this.payload) {
            if(leftTree == null) {
                this.leftTree = new BinaryTree(payload, this.depth+1, 'R');
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

    public BinaryTree getRightTree() {
        return rightTree;
    }

    public String toLexicalJSON(){
        return "{\"depth\": \"" + this.depth + "\", \"position\":\"" + this.position + "\", \"payload\":" + this.payload + "}";
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
}
