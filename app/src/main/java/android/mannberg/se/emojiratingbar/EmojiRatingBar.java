package android.mannberg.se.emojiratingbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mannb on 7/24/2017.
 */

public class EmojiRatingBar extends android.support.v7.widget.AppCompatRatingBar {

    private int[] iconArrayActive =  {
            R.drawable.emoji_hurt000,
            R.drawable.emoji_hurt001,
            R.drawable.emoji_hurt002,
            R.drawable.emoji_hurt003,
            R.drawable.emoji_hurt004,
            R.drawable.emoji_hurt005
    };

    private int[] iconArrayInactive =  {
            R.drawable.emoji_hurt000_bw,
            R.drawable.emoji_hurt001_bw,
            R.drawable.emoji_hurt002_bw,
            R.drawable.emoji_hurt003_bw,
            R.drawable.emoji_hurt004_bw,
            R.drawable.emoji_hurt005_bw
    };

    private TextView msgview = null;

    public EmojiRatingBar(Context context) {
        super(context);
        init();
    }

    public EmojiRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setMax(6);
        this.setNumStars(6);
        this.setStepSize(1.0f);
        this.setRating(1.0f);
    }

    private Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        int stars = getNumStars();
        float rating = getRating();
        float x = 0;

        Bitmap bitmap;
        Resources res = getResources();
        Paint paint = new Paint();

        int W = getWidth();
        int H = getHeight();
        int icon_size = (W/stars)-32;//21 //(H < W)?(H):(W); //72
        if (icon_size > H-16) {
            icon_size = H-16;
        }
        int emoji_y_pos = (H/2)-icon_size/2;

        int delta = ((H > W)?(H):(W))/(stars);
        int offset = (W-(icon_size+(stars-1)*delta))/2;

        for(int i = 0; i < stars; i++) {
            if ((int) rating-1 == i) {
                bitmap = getBitmapFromVectorDrawable(getContext(), iconArrayActive[i]);
            } else {
                bitmap = getBitmapFromVectorDrawable(getContext(), iconArrayInactive[i]);
            }
            x = offset+(i*delta);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, icon_size, icon_size, true);
            canvas.drawBitmap(scaled, x, emoji_y_pos, paint);
            canvas.save();
        }
    }


}
