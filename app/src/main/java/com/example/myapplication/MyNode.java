package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

public class MyNode extends FrameLayout {
    private int num = 0;
    private TextView label;

    public MyNode(Context context) {
        super(context);
    }

    public MyNode(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public void init() {
        label = new TextView(getContext());
        setStyle(0);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(16, 16, 16, 16);
        addView(label, lp);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        setStyle(num);
        if (num == 0) {
            label.setText("");
        } else {
            label.setText(String.valueOf(num));
        }
    }

    private void setStyle(int num){
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(10);
        switch (num){
            case 0:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_0));break;
            case 2:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_2));break;
            case 4:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_4));break;
            case 8:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_8));break;
            case 16:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_16));break;
            case 32:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_32));break;
            case 64:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_64));break;
            case 128:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_128));break;
            case 256:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_256));break;
            case 512:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_512));break;
            case 1024:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_1024));break;
            case 2048:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_2048));break;
            default:drawable.setColor(ContextCompat.getColor(getContext(),R.color.color_over));break;
        }
        label.setBackground(drawable);
        label.setTextSize(40);
        if(num<=4)
            label.setTextColor(Color.GRAY);
        else
            label.setTextColor(Color.WHITE);
        TextPaint textPaint = label.getPaint();
        textPaint.setFakeBoldText(true);
        label.setGravity(Gravity.CENTER);
    }
}
