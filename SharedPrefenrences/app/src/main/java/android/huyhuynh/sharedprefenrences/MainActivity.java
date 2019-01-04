package android.huyhuynh.sharedprefenrences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDangnhap;
    EditText edtName,edtPass;
    CheckBox chkLuu;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ:
        btnDangnhap = findViewById(R.id.btnDangnhap);
        edtName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        chkLuu = findViewById(R.id.chkLuu);

        //Tạo SharedPreferences
        // param1: "luudata" là tên mình đặt > file này là file xml tự tạo khi runapp
        // param2: MODE_PRIVATE mình chọn mặc định
        sharedPreferences = getSharedPreferences("luudata",MODE_PRIVATE);

        //Lấy data đã lưu và set vào:
        edtName.setText(sharedPreferences.getString("user","")); //key đã lưu, tham số thứ 2 là mặc đinh khi không có dữ liệu
        edtPass.setText(sharedPreferences.getString("pass",""));
        chkLuu.setChecked(sharedPreferences.getBoolean("check",false));


        //Sự kiện button
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String pass = edtPass.getText().toString();
                if (name.trim().equals("hao")&&pass.trim().equals("123")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    if (chkLuu.isChecked()){
                        //Lưu các giá trị nếu đăng nhập đúng:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user",name);
                        editor.putString("pass",pass);
                        editor.putBoolean("check",true); //Có thể lưu giá trị check của Checkbox
                        //Lưu ý phải comit:
                        editor.commit();
                    }
                    else {
                        //Trường hợp xoá lưu nếu ko check
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("user");
                        editor.remove("pass");
                        editor.remove("check"); //Có thể lưu giá trị check của Checkbox
                        //Lưu ý phải comit:
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Đăng nhập không thành công",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
