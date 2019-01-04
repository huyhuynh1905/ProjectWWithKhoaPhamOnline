package android.huyhuynh.guesspoke;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class ImageActivity extends Activity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        tableLayout = findViewById(R.id.layoutTableImg);

        //Phát sinh imgview bằng code
        int sodong = 5;
        int socot = 3;

        //Trộn mảng
        Collections.shuffle(MainActivity.arrName);
        for (int i=1;i<=sodong;i++){
            TableRow tableRow = new TableRow(this);
            //tạo cột
            for (int j=1;j<=socot;j++){
                ImageView imageView = new ImageView(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(180,180);
                imageView.setLayoutParams(layoutParams);
                //Lấy ra các hình khác nhau
                final int vitri = socot * (i-1) + j - 1;
                int idHinh = getResources().getIdentifier(MainActivity.arrName.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                //add imgView vào table
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("TenHinh",MainActivity.arrName.get(vitri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            //Add tableRow vào table
            tableLayout.addView(tableRow);
        }
    }
}
