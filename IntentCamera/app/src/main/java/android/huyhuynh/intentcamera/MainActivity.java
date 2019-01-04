package android.huyhuynh.intentcamera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnAnh;
    ImageView imgAnh;
    int REQUEST_FROM_CAMERA =19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAnh = findViewById(R.id.btnChup);
        imgAnh = findViewById(R.id.imgAnh);

        btnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_FROM_CAMERA); //REQUEST_FROM_CAMERA tự đặt giá trị

                //Các dòng code để trong /**/ là code yêu cầu Permission trong API23 trở lên
                /*
                //Chú ý nhớ cấp quyền CAMERA trong Manifest
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},
                        REQUEST_FROM_CAMERA);
                 */
            }
        });
    }
    //Phương thức nhận ảnh từ camera

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check xem từ code nào:
        if (requestCode==REQUEST_FROM_CAMERA&&resultCode==RESULT_OK&&data!=null){
            //Nhận ảnh bằng bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data"); //"data" mặc định
            //set ảnh vào imgview:
            imgAnh.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_FROM_CAMERA&&permissions.length>0&&grantResults[0]==PackageManager){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,REQUEST_FROM_CAMERA_CODE); //REQUEST_FROM_CAMERA tự đặt giá trị
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    */
}
