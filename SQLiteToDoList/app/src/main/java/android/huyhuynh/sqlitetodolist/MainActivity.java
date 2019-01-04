package android.huyhuynh.sqlitetodolist;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBase dataBase;
    ListView lvCongviec;
    ArrayList<CongViec> arrcongviec;
    CongViecAdpter cvAdapter;
    View viewThem,viewSua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCongviec = findViewById(R.id.lvCongviec);

        arrcongviec = new ArrayList<>();
        cvAdapter = new CongViecAdpter(this,R.layout.list_cong_viec,arrcongviec);
        lvCongviec.setAdapter(cvAdapter);

        //Khởi tạo DataBase
        dataBase = new DataBase(this,"ghichu.sqlite",null,1);
        //Tạo bảng
        String creattable = "CREATE TABLE IF NOT EXISTS CongViec(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenCv NVARCHAR(200))";
        dataBase.queryData(creattable);
        //Insert data:

        //Lấy ra:
        hienThiDanhSachCongViec();



    }

    private void hienThiDanhSachCongViec() {
        String laydata = "SELECT * FROM CongViec";
        Cursor data = dataBase.getData(laydata);
        arrcongviec.clear(); //Clear list tránh lặp
        while(data.moveToNext()){
            int id = data.getInt(0);
            String name = data.getString(1);
            CongViec cv = new CongViec(id,name);
            arrcongviec.add(cv);
        }
        cvAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_congviec,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menuAdd){
            dialogThem();
        }
        return super.onOptionsItemSelected(item);
    }
    public void dialogThem(){
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        viewThem = inflater.inflate(R.layout.dialog_them_congviec,null);
        final EditText edtTenviec = viewThem.findViewById(R.id.edtTenviec);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewThem);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tencv = edtTenviec.getText().toString();
                if (tencv.trim().equals("")){
                    Toast.makeText(MainActivity.this,"Chưa nhập việc,Thêm không thành công!",Toast.LENGTH_LONG).show();
                }
                else{
                    //Insert
                    String insertdata = "INSERT INTO CongViec VALUES(null,'"+tencv+"')";
                    dataBase.queryData(insertdata);
                    Toast.makeText(MainActivity.this,"Đã thêm",Toast.LENGTH_LONG).show();
                    hienThiDanhSachCongViec();
                }
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
    public void suaCongViec(final int id, String name){
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        viewSua = inflater.inflate(R.layout.dialog_sua_congviec,null);
        final EditText edtSua = viewSua.findViewById(R.id.edtSua);
        edtSua.setText(name);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(viewSua);
        builder.setTitle("Sửa công việc");
        builder.setIcon(R.drawable.edit);
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tencv = edtSua.getText().toString();
                if (tencv.trim().equals("")){
                    Toast.makeText(MainActivity.this,"Công việc sửa rỗng",Toast.LENGTH_LONG).show();
                }
                else{
                    //Insert
                    String insertdata = "UPDATE CongViec SET TenCv='"+tencv+"' WHERE Id='"+id+"'";
                    dataBase.queryData(insertdata);
                    Toast.makeText(MainActivity.this,"Đã sửa",Toast.LENGTH_LONG).show();
                    hienThiDanhSachCongViec();
                }
            }
        });
        builder.setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public void xoaCongViec(final int id, String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xoá việc");
        builder.setMessage("Bạn muốn xoá công việc này không?");
        builder.setIcon(R.drawable.delete);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String xoa = "DELETE FROM CongViec WHERE Id='"+id+"'";
                dataBase.queryData(xoa);
                Toast.makeText(MainActivity.this,"Đã xoá",Toast.LENGTH_SHORT).show();
                hienThiDanhSachCongViec();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
