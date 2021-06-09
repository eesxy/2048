package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyKernel kernel;
    private MyGrid myGrid;
    private TextView score, state;
    private Button restart;
    private float prevX, prevY, offsetX, offsetY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGrid = findViewById(R.id.myGrid);
        score = findViewById(R.id.score);
        state = findViewById(R.id.state);
        restart = findViewById(R.id.restart);
        kernel = new MyKernel();
        myGrid.init();
        myGrid.setGrid(kernel.board());
        myGrid.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    prevX = event.getX();
                    prevY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    offsetX = event.getX() - prevX;
                    offsetY = event.getY() - prevY;
                    if (Math.abs(offsetX) > Math.abs(offsetY)) {
                        if (offsetX > 30)
                            kernel.right();
                        else if (offsetX < -30)
                            kernel.left();
                    } else {
                        if (offsetY > 30)
                            kernel.down();
                        else if (offsetY < -30)
                            kernel.up();
                    }if(Math.abs(offsetX) >30|| Math.abs(offsetY)>30) {
                        myGrid.setGrid(kernel.board());
                        score.setText("Score\n"+kernel.score());
                        if(kernel.isEnd())
                            state.setText("Game\nOver");
                    }
                    break;
                default:
                    break;
            }
            return true;
        });
        restart.setOnClickListener((v)->{
            kernel = new MyKernel();
            myGrid.setGrid(kernel.board());
            score.setText("Score\n");
            state.setText(R.string.text_2048);
        });
    }
}