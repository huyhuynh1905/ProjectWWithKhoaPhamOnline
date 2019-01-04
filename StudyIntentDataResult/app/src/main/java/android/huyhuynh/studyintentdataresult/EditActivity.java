package android.huyhuynh.studyintentdataresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Button btnConfirm;
    EditText edtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnConfirm = findViewById(R.id.btnConfirm);
        edtEdit = findViewById(R.id.edtEdit);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noidung = edtEdit.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("request_string",noidung);
                //RESULT_OK: có sẵn
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
