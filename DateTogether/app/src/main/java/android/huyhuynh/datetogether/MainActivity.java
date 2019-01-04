package android.huyhuynh.datetogether;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView imgYour,imgTheir;
    TextView txtYour,txtTheir,txtDay;
    String yournameapp,theirnameapp,dateapp;
    Calendar calendarapp = Calendar.getInstance();
    Calendar calendarnow = Calendar.getInstance();
    int REQUEST_AVATAR_YOUR =1,REQUEST_AVATAR_THEIR =2;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        imgYour = findViewById(R.id.imgYour);
        imgTheir = findViewById(R.id.imgTheir);
        txtDay = findViewById(R.id.txtDay);
        txtYour = findViewById(R.id.txtYourname);
        txtTheir = findViewById(R.id.txtTheirname);

        //Dùng sharedPreferences để lưu ngày và tên

        sharedPreferences = getSharedPreferences("datetogether",MODE_PRIVATE);
        //Lấy giá trị từ SharedPreferences:
        imgYour.setImageResource(sharedPreferences.getInt("yourimg",R.drawable.us));
        imgTheir.setImageResource(sharedPreferences.getInt("theirimg",R.drawable.us));
        txtYour.setText(sharedPreferences.getString("yourname","Your Name"));
        txtTheir.setText(sharedPreferences.getString("theirname","Their Name"));
        txtDay.setText(sharedPreferences.getString("demngay","Ngày"));

        //Chèn avatar bằng ảnh chụp
        //importAvatar();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAcount:
                LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();
                final View viewAcount = layoutInflater.inflate(R.layout.acount_activity,null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Your Infomation");
                builder.setIcon(R.drawable.user);
                builder.setView(viewAcount);
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText youname = viewAcount.findViewById(R.id.edtYourname);
                        RadioButton radWoman = viewAcount.findViewById(R.id.radAwoman);
                        if (!youname.getText().toString().trim().equals("")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            yournameapp = youname.getText().toString();
                            txtYour.setText(yournameapp);
                            if (radWoman.isChecked()){
                                imgYour.setImageResource(R.drawable.girl);
                                int girl = getResources().getIdentifier("girl","drawable",getPackageName());
                                editor.putInt("yourimg",girl);
                            } else  {
                                imgYour.setImageResource(R.drawable.boy);
                                int boy = getResources().getIdentifier("boy","drawable",getPackageName());
                                editor.putInt("yourimg",boy);
                            }
                            //Thêm vào lưu
                            editor.putString("yourname",yournameapp);
                            editor.commit();

                        }
                        else Toast.makeText(MainActivity.this,"Enter Your Name!",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();

                break;
            case R.id.menuSetting:
                LayoutInflater layoutInflater1 = MainActivity.this.getLayoutInflater();
                final View viewAcount1 = layoutInflater1.inflate(R.layout.setting_activity,null);
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Settings");
                builder1.setIcon(R.drawable.settings);
                builder1.setView(viewAcount1);
                final EditText datest = viewAcount1.findViewById(R.id.edtDate);
                datest.setFocusable(false);
                datest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nhapNgay(datest);
                    }
                });
                builder1.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText youname = viewAcount1.findViewById(R.id.edtName);
                        RadioButton radWoman = viewAcount1.findViewById(R.id.radWoman);
                        //Đếm ngày:
                        int ngay = (int) ((calendarnow.getTimeInMillis()-calendarapp.getTimeInMillis())/(1000*60*60*24));

                        if (!youname.getText().toString().trim().equals("")&&!datest.getText().toString().equals("")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            theirnameapp = youname.getText().toString();
                            dateapp = datest.getText().toString();
                            txtDay.setText(""+ngay);
                            txtTheir.setText(theirnameapp);
                            if (radWoman.isChecked()){
                                imgTheir.setImageResource(R.drawable.girl);
                                editor.putInt("theirimg",R.drawable.girl);
                            } else {
                                imgTheir.setImageResource(R.drawable.boy);
                                editor.putInt("theirimg",R.drawable.boy);
                            }
                            //Thêm vào lưu:
                            editor.putString("theirname",theirnameapp);
                            editor.putString("demngay",ngay+"");
                            editor.commit();

                        }
                        else Toast.makeText(MainActivity.this,"Enter Your Name And Date( < Now)!",Toast.LENGTH_SHORT).show();

                    }
                });
                builder1.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder1.create().show();
                break;
            case R.id.menuExit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nhapNgay(final EditText edt) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dateint = cal.get(Calendar.DATE);
        calendarnow.set(year,month,dateint);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(year,month,dayOfMonth);
                calendarapp.set(year,month,dayOfMonth);
                SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
                edt.setText(sp.format(cal.getTime()));
            }
        },year,month,dateint);
        datePickerDialog.show();
    }

    //Chọn ảnh đại diện từ camera
    private void importAvatar() {
        imgYour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_AVATAR_YOUR);
            }
        });
        imgTheir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_AVATAR_THEIR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_AVATAR_YOUR&&resultCode==RESULT_OK&&data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgYour.setImageBitmap(bitmap);
        }
        else if (requestCode==REQUEST_AVATAR_THEIR&&resultCode==RESULT_OK&&data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTheir.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
