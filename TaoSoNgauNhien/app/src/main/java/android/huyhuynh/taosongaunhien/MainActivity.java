package android.huyhuynh.taosongaunhien;

import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    TextView txtNumber,txtRandom2;
    Button btnRandom,btnRandom2;
    EditText edtmin, edtmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        conTrol();
    }

    private void init() {
        txtNumber = findViewById(R.id.txtNumber);
        btnRandom = findViewById(R.id.btnRandom);
        txtRandom2 = findViewById(R.id.txtRandom2);
        btnRandom2 = findViewById(R.id.btnRandom2);
        edtmin = findViewById(R.id.edtMin);
        edtmax = findViewById(R.id.edtMax);
    }
    private void conTrol() {
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();
                int number = rd.nextInt(100);
                txtNumber.setText(String.valueOf(number));
            }
        });
        btnRandom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(edtmin.getText().toString());
                int max = Integer.parseInt(edtmax.getText().toString());
                Random rd = new Random();
                int number = rd.nextInt(max-min+1)+min;
                txtRandom2.setText(String.valueOf(number));

            }
        });
    }


}
