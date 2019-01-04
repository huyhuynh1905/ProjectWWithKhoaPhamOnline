package android.huyhuynh.intentimplicit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgWeb,imgTinnhan,imgCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgWeb = findViewById(R.id.imgWeb);
        imgTinnhan = findViewById(R.id.imgTinnhan);
        imgCall = findViewById(R.id.imgCall);

        imgWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mở đến ứng dụng web(vào trang web theo link):
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com.vn/"));

                startActivity(intent);
            }
        });
        imgTinnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                //gửi nội dung tin nhắn, lưu ý "sms_body" ở dưới là mặc định
                intent.putExtra("sms_body","Đây là nội dung tin nhắn");
                //Gửi đến đâu "sms:" dưới đây cũng mặc đinh nhận dạng số phone:
                intent.setData(Uri.parse("sms:0966327151"));
                startActivity(intent);
            }
        });
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL); //Cần quyền android.permission.CALL_PHONE ở Manifest
                //Chuyển sang màn hình gọi điện thoại, "tel:" mặc định nhận dạng tel
                intent.setData(Uri.parse("tel:0966327151"));
                startActivity(intent);
            }
        });
    }
}
