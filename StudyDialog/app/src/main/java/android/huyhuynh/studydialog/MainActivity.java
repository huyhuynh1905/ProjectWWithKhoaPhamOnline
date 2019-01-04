package android.huyhuynh.studydialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Button btnSimple,btnCust,btnLayout,btnLogind;
    View viewLoginl; //DialogLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        conTrol();
    }

    private void init() {
        btnSimple = findViewById(R.id.btnSimlpe);
        btnCust = findViewById(R.id.btnCust);
        btnLayout = findViewById(R.id.btnLayout);
        btnLogind = findViewById(R.id.btnLogind);
    }

    private void conTrol() {
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSimple();
            }
        });
        btnCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCustom();
            }
        });
        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout();
            }
        });
        btnLogind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogincustom();
            }
        });

    }
    //Dialog đơn giản:
    private void dialogSimple(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thông báo!")
                .setIcon(R.drawable.jobsearch)
                .setMessage("Bạn chọn có hay không?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Bạn chọn OK",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Bạn chọn Cancle",Toast.LENGTH_SHORT).show();
                    }
                })
                .setCancelable(false); //Không cho kích ngoài vùng Dialog
        builder.create().show();

    }

    private void dialogCustom(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thông báo!")
                .setMessage("Bạn chọn có hay không?")
                .setPositiveButton("Chấp Nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Bạn chọn Chấp Nhận",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Không Chấp Nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Bạn chọn Không Chấp Nhận",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();

    }
    private void dialogLayout(){
        //Tương tự nhưng tạo layout sau đó lấy đường dẫn gắn vào Dialog bằng lệnh .setView
        //Lấy đường dẫn Layout
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        viewLoginl=inflater.inflate(R.layout.activity_layout_dialog,null);

        //Thực thi Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Thông báo!")
                //.setMessage("Bạn chọn có hay không?")
                .setView(viewLoginl) //setView vào
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name = viewLoginl.findViewById(R.id.edtName); //Phải có viewLogin1
                        EditText pass = viewLoginl.findViewById(R.id.edtPass);
                        String n = name.getText().toString();
                        String p = pass.getText().toString();
                        if (name.getText().toString().trim().length()<=0|pass.getText().toString().length()<=0){
                            Toast.makeText(MainActivity.this,"Vui lòng nhập dữ liệu",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Tên:"+n+"\nPass:"+p,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                /*.setNegativeButton("Không Chấp Nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Bạn chọn Không Chấp Nhận",Toast.LENGTH_SHORT).show();
                    }
                })*/;
        builder.create().show();
    }

    //Customdialog (Tạo một layout riêng)
    private void dialogLogincustom(){
        final Dialog dialog = new Dialog(this);
        //gán layout
        dialog.setContentView(R.layout.custom_dialog_login);
        //Gán tiêu đề:
        dialog.setTitle("Login Form");
        //Ánh xạ các thuộc tính trên Dialog
        final EditText edtUser = dialog.findViewById(R.id.edtUser);
        final EditText edtPass = dialog.findViewById(R.id.edtPass);
        Button btnLogin = dialog.findViewById(R.id.btnLogin);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUser.getText().toString().equals("Hao")&&edtPass.getText().toString().equals("123")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else Toast.makeText(MainActivity.this,"Sai id và pass",Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dùng 1 trong 2 cách để tắt dialog
                dialog.cancel();
                //dialog.dismiss();
            }
        });
        //hiện layout và gán không cho mất khi kích ngoài dialog
        dialog.setCancelable(false);
        dialog.show();

    }
}
