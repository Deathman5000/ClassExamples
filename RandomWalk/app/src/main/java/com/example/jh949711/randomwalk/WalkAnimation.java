package com.example.jh949711.randomwalk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by jh949711 on 3/26/18.
 */

public class WalkAnimation extends View {

    private float cx, cy, radius, sHeight, sWidth;
    private Random r;
    Context c;

    public WalkAnimation(Context context, float w, float h){
        super(context);
        c = context;
        sWidth = w - 100;
        sHeight = h - 100;
        cx = sWidth/2;
        cy = sHeight/2;
        radius = 50;
        r = new Random();
        r.setSeed(System.currentTimeMillis());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(cx, cy, radius, paint);
        updateCoordinates();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            Toast.makeText(c, "Fatal error during the sleep interval", Toast.LENGTH_SHORT).show();
        }
        //invalidate whole view and invoke the callback onDraw()
        invalidate();
    }

    private void updateCoordinates(){
        cx = r.nextFloat()*sWidth;
        cy = r.nextFloat()*sHeight;
        if (cx > sWidth-300) cx -= 500;
        if (cx < 300) cx += 500;
        if (cy > sHeight-300) cy -=500;
        if (cy < 300) cy += 500;
    }
}
