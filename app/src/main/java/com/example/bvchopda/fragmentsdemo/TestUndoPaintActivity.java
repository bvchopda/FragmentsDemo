package com.example.bvchopda.fragmentsdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class TestUndoPaintActivity extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     */
    LinearLayout linearLayout2;
    private ArrayList<Path> undonePaths = new ArrayList<>();
    private ArrayList<Path> paths = new ArrayList<>();
    private ZoomLayout zoomLayout;
    DrawingPanel dp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_undo_paint);
        zoomLayout = (ZoomLayout)findViewById(R.id.zoomLayout);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        ((ToggleButton)findViewById(R.id.toggle)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    zoomLayout.setEnabled(false);
                    dp.setEnabled(true);
                } else {
                    zoomLayout.setEnabled(true);
                    dp.setEnabled(false);
                }
            }
        });
        dp = new DrawingPanel(this);
        linearLayout2.addView(dp);
        ((Button) findViewById(R.id.undo))
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (paths.size() > 0) {
                            undonePaths.add(paths
                                    .remove(paths.size() - 1));
                            dp.invalidate();
                        }
                    }
                });
        ((Button) findViewById(R.id.redo))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (undonePaths.size() > 0) {
                            paths.add(undonePaths.remove(undonePaths.size() - 1));
                            dp.invalidate();
                        }
                    }
                });
    }

    public class DrawingPanel extends View implements View.OnTouchListener {

        private Canvas mCanvas;
        private Path mPath;
        private Paint mPaint, circlePaint, outercirclePaint;

        // private ArrayList<Path> undonePaths = new ArrayList<Path>();
        private float xleft, xright, xtop, xbottom;

        public DrawingPanel(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            this.setOnTouchListener(this);

            circlePaint = new Paint();
            mPaint = new Paint();
            outercirclePaint = new Paint();
            outercirclePaint.setAntiAlias(true);
            circlePaint.setAntiAlias(true);
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLUE);
            outercirclePaint.setColor(Color.BLACK);
            circlePaint.setColor(Color.DKGRAY);
            outercirclePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStyle(Paint.Style.FILL);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(6);
            outercirclePaint.setStrokeWidth(6);
            mCanvas = new Canvas();
            mPath = new Path();
//            paths.add(mPath);
        }

        public void colorChanged(int color) {
            mPaint.setColor(color);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {

            for (Path p : paths) {
                canvas.drawPath(p, mPaint);
            }
            canvas.drawPath(mPath, mPaint);
        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 0;

        private void touch_start(float x, float y) {
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            paths.add(mPath);
            // kill this so we don't double draw
            mPath = new Path();
            undonePaths.clear();
        }

        @Override
        public boolean onTouch(View arg0, MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // if (x <= cx+circleRadius+5 && x>= cx-circleRadius-5) {
                    // if (y<= cy+circleRadius+5 && cy>= cy-circleRadius-5){
                    // paths.clear();
                    // return true;
                    // }
                    // }
                    touch_start(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
                    touch_move(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }
}