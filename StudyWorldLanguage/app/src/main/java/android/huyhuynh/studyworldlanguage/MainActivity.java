package android.huyhuynh.studyworldlanguage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtName,edtEmail,edtPhone;
    TextView txtHienthi;
    Button btnNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        txtHienthi = findViewById(R.id.txtHienthi);
        btnNhap = findViewById(R.id.btnNhap);

        final String txtname = getResources().getString(R.string.txtName);
        final String txtemail = getResources().getString(R.string.txtEmail);
        final String txtphone = getResources().getString(R.string.txtPhone);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                txtHienthi.setText(txtname+": "+name+".\n"+
                                    txtemail+": "+email+".\n"+
                                    txtphone+": "+phone);
            }
        });

    }
}
