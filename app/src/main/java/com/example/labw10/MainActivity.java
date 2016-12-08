package com.example.labw10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    Paint p;
    int width=0, height=0;
    float x, y;
    int touches = 0;
    Field field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        field = new Field(this);
        field.setOnTouchListener(this);
        setContentView(field);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                field.invalidate();
        }
        return true;
    }

    class Field extends View {

        public Field(Context context) {
            super(context);
            p = new Paint();
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            width = w;
            height = h;
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);
            int w = width/8;
            int h = height/10;
            p.setColor(Color.BLACK);
            canvas.drawRect(0,0,w*8,h*7,p);
            p.setColor(Color.WHITE);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i % 2 == j % 2) {
                        canvas.drawRect(i * w, (j-1) * h, (i+1) * w, j * h, p);
                    }
                }
            }
            p.setColor(Color.BLACK);
            p.setTextSize(20);
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(Math.round(x) + ", " + Math.round(y) + "; Количество нажатий: " +
                    touches++, width-300, height-100, p);
        }
    }

}
