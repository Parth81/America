package com.example.nyuscps.america;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by nyuscps on 3/7/15.
 */
public class AmericaView extends View {
    public AmericaView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        float stripeWidth = width / 13.0f;

        canvas.drawColor(Color.WHITE);	//background

        //seven red stripes
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < 13; i += 2) {	//i += 2 means i = i + 2
            //left, top, right, bottom
            canvas.drawRect(i * stripeWidth, 0, (i + 1) * stripeWidth, height, paint);
        }

        //blue union jack
        paint.setColor(Color.BLUE);
        //left, top, right, bottom
        RectF unionJack = new RectF(6.0f * stripeWidth, 0, width, height * .4f);
        canvas.drawRect(unionJack, paint);

        //white five-point star
        paint.setColor(Color.WHITE);

        canvas.save();	//so we can undo the translate and scale
        canvas.translate(unionJack.centerX(), unionJack.centerY());
        float radius = height / 10.0f;
        Path path = new Path();

        for (int i = 0; i <= 2 * 5; i += 2) {
            double theta = 2 * Math.PI * i / 5;	//in radians
            float x = radius * (float)Math.cos(theta);
            float y = radius * (float)Math.sin(theta);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        canvas.drawPath(path, paint);
        canvas.restore();	//undo the scale and translate

        //draw patton.png
        Resources resources = getResources();
        Bitmap bitMap = BitmapFactory.decodeResource(resources, R.mipmap.patton);
        int w = bitMap.getWidth();
        int h = bitMap.getHeight();
        //coordinates of upper left corner of BitMap, paint is null
        canvas.drawBitmap(bitMap, (width - w) / 2.0f, height - h - height / 50, null);
    }
}
