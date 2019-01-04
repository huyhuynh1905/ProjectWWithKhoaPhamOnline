package android.huyhuynh.docbaovoirss;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvList;
    ArrayList<String> arrayListTieuDe,arrayListLink;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvList = findViewById(R.id.lvList);
        arrayListTieuDe = new ArrayList<>();
        arrayListLink = new ArrayList<>();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayListTieuDe);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,LoadWebRss.class);
                intent.putExtra("linkweb",arrayListLink.get(position));
                startActivity(intent);
            }
        });

        new ReadXMLRss().execute("https://vnexpress.net/rss/thoi-su.rss");

    }

    private class ReadXMLRss extends AsyncTask<String,Void,String>{

        StringBuilder rss = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader br = new BufferedReader(inputStreamReader);
                String line ="";
                while ((line=br.readLine())!=null){
                    rss.append(line+"\n");
                }
                br.close();
                inputStreamReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rss.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser xmldomParser = new XMLDOMParser();
            Document document = xmldomParser.getDocument(s);
            NodeList node = document.getElementsByTagName("item");
            for (int i=0;i<node.getLength();i++){
                Element element = (Element) node.item(i);
                String tieude = xmldomParser.getValue(element,"title");
                arrayListTieuDe.add(tieude);
                String link = xmldomParser.getValue(element,"link");
                arrayListLink.add(link);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
