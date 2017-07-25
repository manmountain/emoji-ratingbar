package android.mannberg.se.emojiratingbar;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout base_layout;
    private EmojiRatingBar hurtbar;
    private TextView msgview;
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_card);

        base_layout = (RelativeLayout)findViewById(R.id.base_layout);
        hurtbar = (EmojiRatingBar)findViewById(R.id.emojiratingbar);
        msgview = (TextView) findViewById(R.id.message);

        actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionbar.setDisplayHomeAsUpEnabled(true);

        hurtbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                String msg = "";

                switch ((int)rating) {
                    case 1: msgview.setText(R.string.msg000); msg = "Nice "+getEmojiByUnicode(0x1F4AF)+getEmojiByUnicode(0x1F44A)+getEmojiByUnicode(0x270C); break;
                    case 2: msgview.setText(R.string.msg001); msg = "Hang in there "+getEmojiByUnicode(0x1F60D)+"!! "+getEmojiByUnicode(0x1F48B); break;
                    case 3: msgview.setText(R.string.msg002); msg = "Everything will be alright "+getEmojiByUnicode(0x1F917); break;
                    case 4: msgview.setText(R.string.msg003); msg = "Double hugg attack!! "+getEmojiByUnicode(0x1F917)+getEmojiByUnicode(0x1F917)+getEmojiByUnicode(0x1F60A); break;
                    case 5: msgview.setText(R.string.msg004); msg = "You are the best person I know, everything will be better and I can help you with whatever "+getEmojiByUnicode(0x1F47B); break;
                    case 6: msgview.setText(R.string.msg005); msg = "You little peice of "+getEmojiByUnicode(0x1F4A9)+", grow up "+getEmojiByUnicode(0x1F61C); break;
                    default: msgview.setText(R.string.msg_err); break;
                }
                if (rating > 0) {
                    Snackbar snackbar = Snackbar.make(base_layout, msg, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        hurtbar.setRating(0);
    }

    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
