package android.huyhuynh.guesspoke;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    TextView txtHienthi;
    ImageView imgGuess,imgCheck;
    public static ArrayList<String> arrName;
    int REQUEST_CODE_IMG = 5;
    String tenhinhgoc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgCheck = findViewById(R.id.imgCheck);
        imgGuess = findViewById(R.id.imgGuess);
        txtHienthi = findViewById(R.id.txtHienthi);
        //Lấy mảng tên từ resouce
        String[] arrTen = getResources().getStringArray(R.array.list_img);
        arrName = new ArrayList<>(Arrays.asList(arrTen)); //chứa tên thôi
        //Tạo hình ngẫu nhiên để set ngẫu nhiên từ source
        //Trộn mảng:
        Collections.shuffle(arrName);
        //hinhAnh đây là id của hình
        tenhinhgoc = arrName.get(4);
        int hinhAnh = getResources().getIdentifier(arrName.get(4),"drawable",getPackageName());

        imgCheck.setImageResource(hinhAnh);
        //Xử lí hình ảnh chọn đoán:
        imgGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImageActivity.class);
                startActivityForResult(intent,REQUEST_CODE_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CODE_IMG&&resultCode==RESULT_OK&&data!=null){
            String tenhinhnhan = data.getStringExtra("TenHinh");
            imgGuess.setImageResource(getResources().getIdentifier(tenhinhnhan,"drawable",getPackageName()));
            if (tenhinhnhan.equals(tenhinhgoc)){
                Toast.makeText(MainActivity.this,"Đúng rồi",Toast.LENGTH_SHORT).show();
                new CountDownTimer(2000,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrName);
                        //hinhAnh đây là id của hình
                        tenhinhgoc = arrName.get(4);
                        int hinhAnh = getResources().getIdentifier(arrName.get(4),"drawable",getPackageName());

                        imgCheck.setImageResource(hinhAnh);
                    }
                }.start();
            } else Toast.makeText(MainActivity.this,"Sai rồi!",Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuReload){
            Collections.shuffle(arrName);
            //hinhAnh đây là id của hình
            tenhinhgoc = arrName.get(4);
            int hinhAnh = getResources().getIdentifier(arrName.get(4),"drawable",getPackageName());

            imgCheck.setImageResource(hinhAnh);
        }
        return super.onOptionsItemSelected(item);
    }
}
