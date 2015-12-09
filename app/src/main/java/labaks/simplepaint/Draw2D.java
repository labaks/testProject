package labaks.simplepaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Draw2D extends View {

    public Draw2D(Context context) {
        super(context);
    }

    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(950, 30, 25, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(20, 650, 950, 680, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(32);
        canvas.drawText("Надпись на рисунке", 30, 648, mPaint);

        int x = 810;
        int y = 190;
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(27);
        String str2Rotate = "Лучик солнца!";
        canvas.rotate(-45, x + mRect.exactCenterX(), y + mRect.exactCenterY());
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(str2Rotate, x, y, mPaint);
    }
}
