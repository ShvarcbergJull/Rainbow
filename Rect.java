package com.example.pc.rainbow;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rect {
    int left, top, bottom, right, color;

    public Rect(int left, int top, int right, int bottom, int color) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.color = color;
    }

    public void draw(Canvas canvas)
    {
        Paint p = new Paint();
        p.setColor(color);
        canvas.drawRect(left, top, right, bottom, p);
    }

    public boolean inRect(int point_x, int point_y)
    {
        if (left <= point_x && point_x <= right && top <= point_y && point_y <= bottom)
        {
            return true;
        }
        return false;
    }
}
