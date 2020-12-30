package com.example.android_project.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import android.graphics.Color;

import com.example.android_project.R;

import java.util.Map;

public class RatingsChart extends View {

    private Context context;
    private Map<Integer, Integer> source;
    private Paint paint;


    private final int color1 = Color.rgb(186, 215, 242);
    private final int color2 = Color.rgb(186, 242, 187);
    private final int color3 = Color.rgb(186, 242, 216);
    private final int color4 = Color.rgb(242, 226, 186);
    private final int color5 = Color.rgb(242, 186, 201);

    private final int[] colors = {color1, color2, color3, color4, color5};

    public RatingsChart(Context context,
                        Map<Integer, Integer> source) {
        super(context);
        this.context = context;
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (source == null || source.isEmpty()) {
            drawNoGraph(canvas);
        } else {
            float widthBar = (float) getWidth() / source.size();

            int maxValue = 0;

            for (Integer value : source.values()) {
                if (maxValue < value) {
                    maxValue = value;
                }
            }
            drawValues(canvas, widthBar, maxValue);
        }
    }

    private void drawNoGraph(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(50.0f);
        canvas.drawText(getContext().getString(R.string.statistics_no_visits), getWidth() / 3, getHeight() / 2, paint);
    }

    private void drawValues(Canvas canvas, float widthBar, int maxValue) {
        int currentBarPosition = 0;
        for (Integer label : source.keySet()) {
            int value = source.get(label);

            drawBar(currentBarPosition, canvas, widthBar, maxValue, value);
            drawLegend(canvas, widthBar, currentBarPosition, label, value);

            currentBarPosition++;
        }
    }

    private void drawLegend(Canvas canvas, float widthBar, int currentBarPosition, Integer label, int value) {

        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.1 * getWidth()));


        float x = (float) (currentBarPosition + 0.5) * widthBar;
        float y = (float) 0.95 * getHeight();
        canvas.drawText(String.valueOf(label), x, y, paint);
    }

    private void drawBar(int position, Canvas canvas, float widthBar,
                         int maxValue, int value) {


        float x1 = position * widthBar;
        float y1 = ((1 - (float) value / maxValue) * getHeight());


        float x2 = x1 + widthBar;
        float y2 = getHeight();

        paint.setColor(colors[position]);
        canvas.drawRect(x1, y1, x2, y2, paint);

    }

}
