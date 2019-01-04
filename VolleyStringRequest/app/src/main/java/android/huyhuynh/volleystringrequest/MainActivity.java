package android.huyhuynh.volleystringrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://viblo.asia/p/android-gradle-30-su-dung-implementation-hay-api-WAyK8M2kZxX";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                //Tham số thứ 3 là sự kiện nhận được dữ liệu
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                //Thực thi khi gặp sự kiện lỗi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Lỗi",Toast.LENGTH_SHORT).show();
                        Log.d("LOI",error.toString());
                    }
                }
        );
        //Add vào để thực thi:
        queue.add(stringRequest);
    }
}
