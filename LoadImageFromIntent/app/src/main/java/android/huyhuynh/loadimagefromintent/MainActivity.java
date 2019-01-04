package android.huyhuynh.loadimagefromintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnLoad,btnIntentWeb;
    ImageView imgHinh;
    EditText edtLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad = findViewById(R.id.btnLoad);
        imgHinh = findViewById(R.id.imgHinh);
        edtLink = findViewById(R.id.edtLink);
        btnIntentWeb = findViewById(R.id.btnReadWeb);
        //Chuyển Intetn
        btnIntentWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReadContentInternet.class));
                overridePendingTransition(R.anim.anim_start,R.anim.anim_exit);
            }
        });


        //Xin cấp quyên truy cập Internet ở Manifest
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadHinh().execute(edtLink.getText().toString().trim());
            }
        });
    }
    private class LoadHinh extends AsyncTask<String,Void,Bitmap>{
        //Tham số String đầu tiên chính là kiểu truyền vào, ở đây là String (Link ảnh
        // truyền ở execute())
        //Void do không cần xử lí quá trình cập nhật (tức kiểu của hàm  onProgressUpdate)
        //Bitmap là kiểu trả về của doInBackground
        Bitmap bitmap1 = null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmap1 = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap1;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
        }
    }
}
