package com.example.jh949711.drawshapes;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DrawShapeActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_shape);
    }
    private void setShapeDrawable(Drawable drawable){
        imageView = findViewById(R.id.ImageViewForShape);
        imageView.setImageDrawable(drawable);
    }
    public void drawLine(View view){
        ShapeDrawable d = new ShapeDrawable(new RectShape());
        d.setIntrinsicHeight(3);
        d.setIntrinsicWidth(800);
        d.getPaint().setColor(Color.BLACK);
        setShapeDrawable(d);
    }
    public void drawOval(View view){
        ShapeDrawable d = new ShapeDrawable(new OvalShape());
        d.setIntrinsicHeight(500);
        d.setIntrinsicWidth(800);
        d.getPaint().setColor(Color.GREEN);
        setShapeDrawable(d);
    }
    public void drawRectangle(View view){
        ShapeDrawable d = new ShapeDrawable(new RectShape());
        d.setIntrinsicHeight(400);
        d.setIntrinsicWidth(800);
        d.getPaint().setColor(Color.RED);
        setShapeDrawable(d);
    }
    public void drawPath1(View view){
        Path p = new Path();
        p.moveTo(500,0);
        p.lineTo(150,1000);
        p.lineTo(1000,375);
        p.lineTo(0,375);
        p.lineTo(800,1000);
        p.lineTo(500,0);
        ShapeDrawable d = new ShapeDrawable(new PathShape(p,1000,1000));
        d.setIntrinsicWidth(1000);
        d.setIntrinsicHeight(1000);
        d.getPaint().setColor(Color.CYAN);
        setShapeDrawable(d);
    }
    public void drawPath2(View view){
        Path p = new Path();
        p.moveTo(500,0);
        p.lineTo(150,1000);
        p.lineTo(1000,375);
        p.lineTo(0,375);
        p.lineTo(800,1000);
        p.lineTo(500,0);
        p.setFillType(Path.FillType.EVEN_ODD);
        ShapeDrawable d = new ShapeDrawable(new PathShape(p,1000,1000));
        d.setIntrinsicWidth(1000);
        d.setIntrinsicHeight(1000);
        d.getPaint().setColor(Color.MAGENTA);
        setShapeDrawable(d);
    }
    public void drawPath3(View view){
        Path p = new Path();
        p.moveTo(500,0);
        p.lineTo(150,1000);
        p.lineTo(1000,375);
        p.lineTo(0,375);
        p.lineTo(800,1000);
        p.lineTo(500,0);
        ShapeDrawable d = new ShapeDrawable(new PathShape(p,1000,1000));
        d.setIntrinsicWidth(1000);
        d.setIntrinsicHeight(1000);
        d.getPaint().setColor(Color.BLACK);
        d.getPaint().setStyle(Paint.Style.STROKE);
        setShapeDrawable(d);
    }
}
