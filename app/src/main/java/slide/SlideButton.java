package slide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by F on 2017-05-02.
 */public class SlideButton extends android.support.v7.widget.AppCompatSeekBar {

    private Drawable thumb;
    private SlideButtonListener listener;

    public SlideButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        this.thumb = thumb;
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(),0);

        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /** if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (thumb.getBounds().contains((int) event.getX(), (int) event.getY())) {
                super.onTouchEvent(event);
            } else
                return false;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getProgress() > 70)
                handleSlide();

            setProgress(0);
        } else
            super.onTouchEvent(event);

        return true;
    }*/
        int i;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int x= (int) event.getX();
                    int y= (int) event.getY();
                    if (!thumb.getBounds().contains((int) event.getY(), (int) event.getX())) {
                        return false;
                    }
                }
            case MotionEvent.ACTION_MOVE:
                i=getMax() - (int) (getMax() * event.getY() / getHeight());
                setProgress(100 - i);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;
            case MotionEvent.ACTION_UP:
                i=getMax() - (int) (getMax() * event.getY() / getHeight());
                if (i < 30) {
                    handleSlide();
                }
                setProgress(0);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private void handleSlide() {
        listener.handleSlide();
    }

    public void setSlideButtonListener(SlideButtonListener listener) {
        this.listener = listener;
    }
}