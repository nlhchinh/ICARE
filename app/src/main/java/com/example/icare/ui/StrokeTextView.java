package com.example.icare.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class StrokeTextView extends androidx.appcompat.widget.AppCompatTextView {

    private Paint strokePaint;

    public StrokeTextView(Context context) {
        super(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(4); // weight = 2dp x2 (vì stroke là radius)
        strokePaint.setColor(Color.parseColor("#742521"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Vẽ stroke trước
        strokePaint.setTextSize(getTextSize());
        strokePaint.setTypeface(getTypeface());
        strokePaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(getText().toString(), 0, getBaseline(), strokePaint);

        // Vẽ fill sau
        super.onDraw(canvas);
    }
}

