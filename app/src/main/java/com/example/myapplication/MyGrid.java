package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.GridLayout;

public class MyGrid extends GridLayout {
    private MyNode[][] grid;

    public MyGrid(Context context) {
        super(context);
    }

    public MyGrid(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void init() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int size = metrics.widthPixels;
        int nodeSize = size / 4;
        grid = new MyNode[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                MyNode node = new MyNode(getContext());
                node.init();
                addView(node, nodeSize, nodeSize);
                grid[i][j] = node;
            }
        }
    }

    public void setGrid(int[][] numGrid) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                grid[i][j].setNum(numGrid[i][j]);
            }
        }
    }

    private int dp2px(int num){
        float scale = getResources().getDisplayMetrics().density;
        return (int)(num*scale+0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(heightSize, widthSize);
        setMeasuredDimension(size, size);
    }

}
