package android.huyhuynh.studydrablecustom;

import android.graphics.drawable.ClipDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    Button btnTest;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest = findViewById(R.id.button);
        imgHinh = findViewById(R.id.imgHinh);
        progressBar = findViewById(R.id.progressBar);

        imgHinh.setImageLevel(0000);
        final ClipDrawable clipDrawable = (ClipDrawable) imgHinh.getDrawable();

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgHinh.setImageLevel(0000);
                progressBar.setProgress(0);
                progressBar.setSecondaryProgress(10);
                CountDownTimer countDownTimer = new CountDownTimer(11000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        imgHinh.setImageLevel(clipDrawable.getLevel()+1000);
                    }
                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progressBar.getProgress()+5);
                        progressBar.setSecondaryProgress(progressBar.getSecondaryProgress()+5);
                        if(progressBar.getProgress()>=100){
                            progressBar.setProgress(0);
                            progressBar.setSecondaryProgress(5);
                        }
                        handler.postDelayed(this,500);
                    }
                },1000);
            }
        });
    }
}
