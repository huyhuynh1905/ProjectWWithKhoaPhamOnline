package android.huyhuynh.studycountdowntimer;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mtxtTimer;
    Button mbtncountdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        conTrol();
    }

    private void init() {
        mbtncountdown = findViewById(R.id.btncountdown);
        mtxtTimer = findViewById(R.id.txtTimer);
    }

    private void conTrol() {
        mbtncountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbtncountdown.setEnabled(false); //Khoá button tạm thời
                countDownTimer();
            }
        });
    }

    private void countDownTimer() {
        CountDownTimer count = new CountDownTimer(10000,1000) {
            int time = 10;
            @Override
            public void onTick(long millisUntilFinished) {
                //Mỗi một giây thực hiện 1 công việc gì đó!
                time =time-1;
                mtxtTimer.setText(String.valueOf(time));
            }

            @Override
            public void onFinish() {
                //Khi thực hiện xong thực hiện công việc gì đó
                mtxtTimer.setText("Xong");
                Toast.makeText(MainActivity.this,"Xong rồi",Toast.LENGTH_SHORT).show();
                mbtncountdown.setEnabled(true);

            }
        };
        count.start();
    }
}
