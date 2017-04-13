package ekordik.cuw.edu.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private BinaryTree tree;
    private ViewGroup display;
    private EditText input;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tree = null;
        display = (ViewGroup)findViewById(R.id.displayLayout);
        input = (EditText)findViewById(R.id.editText);
        level = 0;
    }

    public void addClicked(View view) {
        int num = Integer.parseInt(input.getText().toString());
        if(tree == null){
            tree = new BinaryTree(num);
        }else {
            tree.add(num);
        }
        displayTree(this.tree);
        input.setText("");
    }

    public void displayInOrderClicked(View view) {
        this.display.removeAllViews();
        tree.displayInOrder(tree, this.display, this);

    }

    private void displayTree(BinaryTree tree) {
        display.removeAllViews();
        level = 1;
        Queue<BinaryTree> currentLevel = new LinkedList<>();
        Queue<BinaryTree> nextLevel = new LinkedList<>();
        currentLevel.add(tree);
        String text = "";
        while(!currentLevel.isEmpty()){
            Iterator<BinaryTree> iterator = currentLevel.iterator();
            while(iterator.hasNext()) {
                BinaryTree currentTree = iterator.next();
                if(currentTree == null) {
                    text += "null  ";
                }else {
                    if (currentTree.getLeftTree() != null) {
                        nextLevel.add(currentTree.getLeftTree());
                    } else {
                        nextLevel.add(null);
                    }
                    if (currentTree.getRightTree() != null) {
                        nextLevel.add(currentTree.getRightTree());
                    } else {
                        nextLevel.add(null);
                    }
                    text += currentTree.getPayload() + "   ";
                }
            }
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER);
            tv.setText(text);
            display.addView(tv);
            text = "";
            currentLevel = nextLevel;
            level++;
            nextLevel = new LinkedList<>();
        }
    }
}
