package android.huyhuynh.studyintentsenddata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnSend;
    EditText edtNoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSend = findViewById(R.id.btnSend);
        edtNoidung = findViewById(R.id.edtNoidunggui);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
                //Gửi String trước khi startActivity2:
                String noidung = "Xin chào!";
                intent.putExtra("ID01",edtNoidung.getText().toString()); //Mình gửi kiểu String với name là ID01

                //Tiếp tục truyền và nhận các kiểu dữ kiệu:
                //1.Số:
                intent.putExtra("ID02",2018);
                //2.Mảng:
                String[] name = {"Huy","Bảo","Huỳnh"};
                intent.putExtra("ID03",name);
                //3.Gửi một Object (Chú ý class của Object phải implements Serializable):
                HocSinh hs1 = new HocSinh("Huy",20);
                HocSinh hs2 = new HocSinh("Huỳnh",21);
                ArrayList<HocSinh> arhs = new ArrayList<>();
                arhs.add(hs1);
                arhs.add(hs2);
                intent.putExtra("ID04",arhs);
                //Đặc biệt gửi 1 gói lớn nhiều kiểu dữ liệu (gửi tất cả ở trên)
                Bundle bundle = new Bundle();

                bundle.putString("ID01",noidung); //Chuỗi
                bundle.putInt("ID02",113);          //Số
                bundle.putStringArray("ID03",name); //Mảng
                bundle.putSerializable("ID04",hs1); //Đối tượng
                bundle.putSerializable("ID05",arhs); //ArrayList

                intent.putExtra("DuLieu",bundle); //Lệnh gửi


                startActivity(intent);
            }
        });
    }
}
