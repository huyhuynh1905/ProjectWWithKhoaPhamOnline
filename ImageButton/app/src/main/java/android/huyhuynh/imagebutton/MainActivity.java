package android.huyhuynh.imagebutton;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageButton igmbtnAnh;
    RelativeLayout layoutback;
    ArrayList<Integer> arrayImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        conTrol();
    }

    private void init() {
        layoutback = findViewById(R.id.layoutBack);
        igmbtnAnh = findViewById(R.id.imgbtnAnh);
        igmbtnAnh.setImageResource(R.drawable.button);
        arrayImage = new ArrayList<>();
        arrayImage.add(R.drawable.p1);
        arrayImage.add(R.drawable.p2);
        arrayImage.add(R.drawable.p3);
        arrayImage.add(R.drawable.p4);
    }

    private void conTrol() {
        layoutback.setBackgroundColor(Color.BLUE);
        igmbtnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();
                int vitri=rd.nextInt(arrayImage.size());
                layoutback.setBackgroundResource(arrayImage.get(vitri));
                Snackbar snackbar = Snackbar.make(layoutback,"Đã đổi!",Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });
    }
}
