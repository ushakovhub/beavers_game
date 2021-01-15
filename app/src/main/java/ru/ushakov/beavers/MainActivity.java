package ru.ushakov.beavers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // блоки для изображений
    private ImageView hole1, hole2, hole3;

    // метки для показа Beaver
    private boolean isBeaver1, isBeaver2, isBeaver3;

    private Button btnStart;
    private TextView tvScore;
    private Integer mScore = 0;

    // таймер для игры
    private MyTimer myTimer;
    private final Long mInterval = 500 * 1L, mStartTime = 1000 * 30L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hole1 = (ImageView) findViewById(R.id.iv_bvr1);
        hole2 = (ImageView) findViewById(R.id.iv_bvr2);
        hole3 = (ImageView) findViewById(R.id.iv_bvr3);

        tvScore = (TextView) findViewById(R.id.tv_score);

        hole1.setImageResource(R.drawable.ic_hole);
        hole2.setImageResource(R.drawable.ic_hole);
        hole3.setImageResource(R.drawable.ic_hole);

        isBeaver1 = false;
        isBeaver2 = false;
        isBeaver3 = false;

        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBeaver1){
                    mScore++;
                    tvScore.setText( mScore.toString() );
                }

            }
        });

        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBeaver2){
                    mScore++;
                    tvScore.setText( mScore.toString() );
                }

            }
        });

        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBeaver3){
                    mScore++;
                    tvScore.setText( mScore.toString() );
                }

            }
        });

        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setClickable(false);

                mScore = 0;
                tvScore.setText( mScore.toString() );

                myTimer = new MyTimer(mStartTime, mInterval);
                myTimer.start();
            }
        });

    }

    // рандомный показ Beaver
    private boolean setRandBeaver(ImageView iv){

        if (Math.random() < 0.3){
            iv.setImageResource(R.drawable.ic_beaver);
            return true;
        }else {
            iv.setImageResource(R.drawable.ic_hole);
            return false;
        }

    }

    private class MyTimer extends CountDownTimer {

        public MyTimer(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }
        // onTick выполняется
        @Override
        public void onTick(long time) {

            isBeaver1 = setRandBeaver(hole1);
            isBeaver2 = setRandBeaver(hole2);
            isBeaver3 = setRandBeaver(hole3);

            btnStart.setText( String.valueOf(time / 1000) );

        }

        @Override
        public void onFinish() {

            hole1.setImageResource(R.drawable.ic_hole);
            hole2.setImageResource(R.drawable.ic_hole);
            hole3.setImageResource(R.drawable.ic_hole);

            isBeaver1 = false;
            isBeaver2 = false;
            isBeaver3 = false;

            btnStart.setText(R.string.button_start_again);
            btnStart.setClickable(true);

        }
    }
}