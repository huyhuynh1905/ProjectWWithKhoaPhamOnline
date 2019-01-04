package android.huyhuynh.sqlitetodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CongViecAdpter extends BaseAdapter {

    private MainActivity context; //MainActivity để gọi các phương thức trực tiếp từ màn hình Main
    private int layout;
    private ArrayList<CongViec> congViecs;

    public CongViecAdpter(MainActivity context, int so, ArrayList<CongViec> congViecs) {
        this.context = context;
        this.layout = so;
        this.congViecs = congViecs;
    }

    @Override
    public int getCount() {
        return congViecs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //Tạo viewhodler
    private class ViewHolder{
        TextView txtTen;
        ImageView imgEdit,imgDelete;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.txtTen = convertView.findViewById(R.id.txtTenviec);
            viewHolder.imgEdit = convertView.findViewById(R.id.imgEdit);
            viewHolder.imgDelete = convertView.findViewById(R.id.imgDelete);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final CongViec cv = congViecs.get(position);
        viewHolder.txtTen.setText(cv.getName());
        //Bắt sự kiện xoá và sửa
        viewHolder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.suaCongViec(cv.getId(),cv.getName()); //Gọi các phương thức ở màn hình main
            }
        });
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoaCongViec(cv.getId(),cv.getName()); //Gọi các phương thức ở màn hình main
            }
        });
        return convertView;
    }

}
