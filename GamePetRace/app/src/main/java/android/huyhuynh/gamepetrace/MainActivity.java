package android.huyhuynh.gamepetrace;

import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnRun;
    CheckBox ckpika,ckmew,ckduck;
    SeekBar seepika,seemew,seeduck;
    TextView txtPrice;
    int price = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        init();
        checkSetPet();
        conTrol();
    }

    private void init() {
        btnRun = findViewById(R.id.btnRun);
        ckpika = findViewById(R.id.ckpika);
        ckmew = findViewById(R.id.ckmewth);
        ckduck = findViewById(R.id.ckduck);
        seepika = findViewById(R.id.seepika);
        seemew = findViewById(R.id.seemmew);
        seeduck = findViewById(R.id.seeduck);
        txtPrice = findViewById(R.id.txtPrice);


        txtPrice.setText(price+"");
        //Không cho kéo seekbar:
        seepika.setEnabled(false);
        seemew.setEnabled(false);
        seeduck.setEnabled(false);
    }

    private void conTrol() {
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckpika.isChecked()|ckmew.isChecked()|ckduck.isChecked()) {
                    resetPet();
                    setDisable();
                    runPetCountDown();
                }
                else {
                    Toast.makeText(MainActivity.this, "Bạn chưa đặt cược!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void runPetCountDown(){
        final CountDownTimer runanyway = new CountDownTimer(60000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                Random rd =new Random();
                int pika = rd.nextInt(5)+1;
                int mew = rd.nextInt(5)+1;
                int duck = rd.nextInt(5)+1;

                if (seepika.getProgress()>=seepika.getMax()){
                    //đang trong count nên dùng this thay vì tên biến, this.cancel chỉ chạy được trên Androi 5.0 trở lên!
                    this.cancel();
                    setEnable();
                    if (ckpika.isChecked()){
                        price +=10;
                        Toast.makeText(MainActivity.this, "You Win! Price +10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                    else {
                        price -=10;
                        Toast.makeText(MainActivity.this, "You Lose! Price -10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                }
                else if (seemew.getProgress()>=seemew.getMax()){
                    this.cancel();
                    setEnable();
                    if (ckmew.isChecked()){
                        price +=10;
                        Toast.makeText(MainActivity.this, "You Win! Price +10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                    else {
                        price -=10;
                        Toast.makeText(MainActivity.this, "You Lose! Price -10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                }
                else if (seeduck.getProgress()>=seeduck.getMax()){
                    this.cancel();
                    setEnable();
                    if (ckduck.isChecked()){
                        price +=10;
                        Toast.makeText(MainActivity.this, "You Win! Price +10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                    else {
                        price -=10;
                        Toast.makeText(MainActivity.this, "You Lose! Price -10.", Toast.LENGTH_SHORT).show();
                        txtPrice.setText(price+"");
                    }
                }


                seepika.setProgress(seepika.getProgress()+pika);
                seemew.setProgress(seemew.getProgress()+mew);
                seeduck.setProgress(seeduck.getProgress()+duck);
            }

            @Override
            public void onFinish() {

            }
        };
        runanyway.start();
    }
    public void resetPet(){
        seepika.setProgress(0);
        seemew.setProgress(0);
        seeduck.setProgress(0);
    }

    public void setDisable(){
        btnRun.setEnabled(false);
        ckpika.setEnabled(false);
        ckmew.setEnabled(false);
        ckduck.setEnabled(false);
    }
    public void setEnable(){
        btnRun.setEnabled(true);
        ckpika.setEnabled(true);
        ckmew.setEnabled(true);
        ckduck.setEnabled(true);
    }

    public void checkSetPet(){
        ckpika.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckmew.setChecked(false);
                    ckduck.setChecked(false);
                }
            }
        });
        ckmew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckpika.setChecked(false);
                    ckduck.setChecked(false);
                }
            }
        });
        ckduck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ckpika.setChecked(false);
                    ckmew.setChecked(false);
                }
            }
        });
    }
}
