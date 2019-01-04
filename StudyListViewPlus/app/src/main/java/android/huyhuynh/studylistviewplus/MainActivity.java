package android.huyhuynh.studylistviewplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTraiCay;
    ArrayList<TraiCay> traiCays = new ArrayList<>();
    TraiCayAdapter traiCayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        lvTraiCay = findViewById(R.id.lvTraiCay);
        traiCays.add(new TraiCay("Táo","Táo Đà Lạt",R.drawable.apple));
        traiCays.add(new TraiCay("Chuối", "Chuối bà Thanh Hoá",R.drawable.banana));
        traiCays.add(new TraiCay("Nho", "Nho nhập khẩu Mỹ",R.drawable.grapes));
        traiCays.add(new TraiCay("Cam", "Cam xứ Huế",R.drawable.orange));
        traiCays.add(new TraiCay("Dứa", "Dứa Ấn Độ Chín",R.drawable.pineapple));
        traiCays.add(new TraiCay("Dâu Tây", "Dâu tây Đà Lạt",R.drawable.strawberry));
        traiCays.add(new TraiCay("Cà Chua", "Cà chua xuất xứ Trung Quốc",R.drawable.tomato));
        traiCays.add(new TraiCay("Táo","Táo Đà Lạt",R.drawable.apple));
        traiCays.add(new TraiCay("Chuối", "Chuối bà Thanh Hoá",R.drawable.banana));
        traiCays.add(new TraiCay("Nho", "Nho nhập khẩu Mỹ",R.drawable.grapes));
        traiCays.add(new TraiCay("Cam", "Cam xứ Huế",R.drawable.orange));
        traiCays.add(new TraiCay("Dứa", "Dứa Ấn Độ Chín",R.drawable.pineapple));
        traiCays.add(new TraiCay("Dâu Tây", "Dâu tây Đà Lạt",R.drawable.strawberry));
        traiCays.add(new TraiCay("Cà Chua", "Cà chua xuất xứ Trung Quốc",R.drawable.tomato));
        traiCays.add(new TraiCay("Táo","Táo Đà Lạt",R.drawable.apple));
        traiCays.add(new TraiCay("Chuối", "Chuối bà Thanh Hoá",R.drawable.banana));
        traiCays.add(new TraiCay("Nho", "Nho nhập khẩu Mỹ",R.drawable.grapes));
        traiCays.add(new TraiCay("Cam", "Cam xứ Huế",R.drawable.orange));
        traiCays.add(new TraiCay("Dứa", "Dứa Ấn Độ Chín",R.drawable.pineapple));
        traiCays.add(new TraiCay("Dâu Tây", "Dâu tây Đà Lạt",R.drawable.strawberry));
        traiCays.add(new TraiCay("Cà Chua", "Cà chua xuất xứ Trung Quốc",R.drawable.tomato));

        traiCayAdapter = new TraiCayAdapter(this,R.layout.trai_cay_app_layout,traiCays);
        lvTraiCay.setAdapter(traiCayAdapter);

        


    }
}
