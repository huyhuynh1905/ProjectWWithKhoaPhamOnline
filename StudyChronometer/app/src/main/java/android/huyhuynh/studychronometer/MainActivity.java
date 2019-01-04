package android.huyhuynh.studychronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Chronometer chronometer;
    Button btnstart,btnpause,btnstop;
    boolean flag =false;
    long timewhenpause=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        conTrol();
    }

    private void init() {
        chronometer = findViewById(R.id.chronometer);
        btnstart = findViewById(R.id.btnstart);
        btnpause = findViewById(R.id.btnpause);
        btnstop = findViewById(R.id.btnstop);

        btnstart.setOnClickListener(this);
        btnpause.setOnClickListener(this);
        btnstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnstart:
                if (!flag){
                    long timestart = SystemClock.elapsedRealtime() + timewhenpause;
                    chronometer.setBase(timestart);
                    chronometer.start();
                    flag=true;
                }
                break;
            case R.id.btnpause:
                if (flag){
                    chronometer.stop();
                    timewhenpause = chronometer.getBase() - SystemClock.elapsedRealtime();
                    flag=false;
                }
                break;
            case R.id.btnstop:
                if (flag){
                    chronometer.stop();
                    flag=false;
                }
                break;
        }
    }

    private void conTrol() {
    }
}
