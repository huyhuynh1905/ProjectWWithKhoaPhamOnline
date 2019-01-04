package android.huyhuynh.studyintentsenddata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivitySecond extends AppCompatActivity {
    TextView txtNhan,txtMang,txtSo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtNhan = findViewById(R.id.txtNhan);
        txtMang=findViewById(R.id.txtMang);
        txtSo=findViewById(R.id.txtSo);

        //Nhận nội dung
        Intent intent = getIntent();
        String noidungnhan = intent.getStringExtra("ID01"); //Nhận bằng ID01
        //Nhận số:
        int nhanso = intent.getIntExtra("ID02",101);//101 là giá trị mặc định nếu sai id hoặc sai kiểu dữ liệu
        txtSo.setText(""+nhanso);
        //Nhận mảng:
        String[] nhanmang = intent.getStringArrayExtra("ID03");
        //Nhận đối tượng:
        ArrayList<HocSinh> arhs = new ArrayList<>();
        arhs = (ArrayList<HocSinh>) intent.getSerializableExtra("ID04");
        for (HocSinh hs :arhs){
            txtMang.append(hs.getName()+" - "+hs.getAge()+"\n");
        }

        //Nhận Bundle:
        Bundle bundle = intent.getBundleExtra("DuLieu");
        
        String chuoi = bundle.getString("ID01");
        int so = bundle.getInt("ID02");
        String[] mang = bundle.getStringArray("ID03");
        HocSinh hs = (HocSinh) bundle.getSerializable("ID04");
        ArrayList<HocSinh> manghs = (ArrayList<HocSinh>) bundle.getSerializable("ID05");

        txtNhan.setText(noidungnhan);

    }
}
