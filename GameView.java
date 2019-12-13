package com.example.pc.rainbow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Paint;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends View {
    MainActivity activity;
    ArrayList<Circle> circles = new ArrayList<>();
    int index_circle;
    int n;
    int width = getWidth();
    int height = getHeight();
    float x = width/2, y = height/2;
    int start_circle = 0;
    boolean visit = false;
    Rect r;

    private void start() {
        n = 5 + (int) (Math.random() * 10);
        for (int i = 0;i < n;i++)
        {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * (height/2));
            int radius = 50 + (int) (Math.random() * 200);
            Circle temp = new Circle(x, y, radius, i, Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
            circles.add(temp);
        }
    }

    public GameView(Context context) {
        super(context);
        activity = (MainActivity) context;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = start_circle;i < n;i++)
        {
            circles.get(i).draw(canvas);
        }
        r = new Rect(0, height - 400, width, height, circles.get(start_circle).color);
        r.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            Log.d("MyActionsCool", "down");
            for (int i = start_circle;i < n;i++)
            {
                if (circles.get(i).inCircle(event.getX(), event.getY()))
                {
                    visit = true;
                    index_circle = i;
                    break;
                }
            }
        if (event.getAction() == MotionEvent.ACTION_MOVE && visit == true)
        {
            Log.d("MyActionsCool", "move");
            circles.get(index_circle).x = (int)event.getX();
            circles.get(index_circle).y = (int)event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_UP && visit == true)
        {
            if (r.inRect((int)event.getX(), (int)event.getY()))
            {
                if (circles.get(index_circle).color == r.color)
                {
                    start_circle += 1;
                    Log.d("MyActionsCool", "yes");
                    if (start_circle == n)
                    {
                        Toast ending = Toast.makeText(activity, "Вы победили!", Toast.LENGTH_LONG);
                        ending.show();
                        circles.clear();
                        start();
                        start_circle = 0;
                    }
                }
            }
            Log.d("MyActionsCool", "up");
            index_circle = -1;
            visit = false;
        }

        invalidate();
        return true;
    }
}
