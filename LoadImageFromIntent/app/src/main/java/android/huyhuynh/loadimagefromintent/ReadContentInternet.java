package android.huyhuynh.loadimagefromintent;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ReadContentInternet extends AppCompatActivity {
    Button btnMain,btnWeb;
    TextView txtWeb;
    EditText edtWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content_internet);
        //Ánh xạ:
        btnMain = findViewById(R.id.btnMain);
        btnWeb = findViewById(R.id.btnLoadWeb);
        txtWeb = findViewById(R.id.txtWeb);
        edtWeb = findViewById(R.id.edtLinkWeb);
        //Chuyển intent
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadContentInternet.this,MainActivity.class));
                overridePendingTransition(R.anim.anim_start,R.anim.anim_exit);
            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadWeb().execute(edtWeb.getText().toString().trim());
            }
        });
    }
    private class LoadWeb extends AsyncTask<String,Void,String>{
        String source = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = url.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String m="";
                while ((m=bufferedReader.readLine())!=null){
                    source+=m+"\n";
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return source;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtWeb.setText(s);
        }
    }
}
