package android.huyhuynh.jsonarrayobject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvDanhsach;
    ArrayAdapter<String> khoahoc;
    ArrayList<String> arKhoahoc;
    String ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDanhsach = findViewById(R.id.lvDanhsach);
        arKhoahoc = new ArrayList<>();
        khoahoc = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arKhoahoc);
        lvDanhsach.setAdapter(khoahoc);

        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json");
    }
    private class ReadJSON extends AsyncTask<String,Void,String>{
        StringBuilder builder = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inpr = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader br = new BufferedReader(inpr);
                String line ="";
                while((line = br.readLine())!=null){
                     builder.append(line);
                }
                br.close();
                inpr.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return builder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ob=s;
            readArrayJSON();
        }
    }

    public void readArrayJSON(){
        try {
            JSONArray arr = new JSONArray(ob);
            for (int i=0;i<arr.length();i++){
                JSONObject job = arr.getJSONObject(i);
                String tenkhoa = job.getString("khoahoc");
                String hocphi = job.getString("hocphi");
                arKhoahoc.add(tenkhoa+" - "+hocphi);
                khoahoc.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
