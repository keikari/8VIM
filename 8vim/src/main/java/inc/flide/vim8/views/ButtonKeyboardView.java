package inc.flide.vim8.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

import inc.flide.vim8.geometry.Dimention;
import inc.flide.vim8.keyboardHelpers.InputMethodViewHelper;
import inc.flide.vim8.structures.Constants;

public abstract class ButtonKeyboardView extends KeyboardView {

    public ButtonKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Dimention computedDimension = InputMethodViewHelper.onMeasureHelper(
                                                    MeasureSpec.getSize(widthMeasureSpec),
                                                    MeasureSpec.getSize(heightMeasureSpec),
                                                    getResources().getConfiguration().orientation);

        setMeasuredDimension(computedDimension.getWidth(), computedDimension.getHeight());
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(Constants.TEXT_SIZE);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "SF-UI-Display-Regular.otf");
        paint.setTypeface(font);

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for(Keyboard.Key key: keys) {
            if(key.label != null)
                canvas.drawText(key.label.toString(), (key.x*2 + key.width)/2, (key.y*2 + key.height)/2, paint);
            if(key.icon != null) {
                int side = key.height;
                if (key.width < key.height) {
                    side = key.width;
                }
                key.icon.setBounds(key.x+(side/4), key.y+(side/4), key.x + (side*3/4), key.y + (side*3/4));
                key.icon.draw(canvas);
            }
        }
    }

}
