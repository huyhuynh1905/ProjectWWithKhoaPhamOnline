package android.huyhuynh.studyintentdataresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnEdit;
    TextView txtHienthi;

    //Khai báo một biến Request code kiểu int bất kì
    int REQUEST_CODE_RANDOM = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEdit = findViewById(R.id.btnEdit);
        txtHienthi = findViewById(R.id.txtHienthi);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditActivity.class);
                //Chú ý:
                startActivityForResult(intent,REQUEST_CODE_RANDOM);
            }
        });
    }

    //Phương thức lấy dữ liệu từ màn hình trước trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Kiểm tra điều kiện xem nhận có đúng không:
        //1.requestCode có giống với REQUEST_CODE_RANDOM mình gửi đi không?
        //2.resultCode có giống với RESULT_OK trả về không?
        if (requestCode == REQUEST_CODE_RANDOM && resultCode == RESULT_OK){
            String noidungnhanve = data.getStringExtra("request_string");
            txtHienthi.setText(noidungnhanve);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
