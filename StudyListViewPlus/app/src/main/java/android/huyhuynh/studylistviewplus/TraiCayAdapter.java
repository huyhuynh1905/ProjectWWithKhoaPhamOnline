package android.huyhuynh.studylistviewplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {

    private Context context; //như là truyền MainActivity
    private int layout;
    private List<TraiCay> traiCays;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCays) {
        this.context = context;
        this.layout = layout;
        this.traiCays = traiCays;
    }

    @Override
    public int getCount() {
        //Trả về số dòng hiển thị của List View
        return traiCays.size();  //Trả về listsize
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Trả về mỗi dòng trên cái adapter
        //khai báo layoutinflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        //ánh xạ view
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtNote = convertView.findViewById(R.id.txtNote);
        ImageView imgHinh = convertView.findViewById(R.id.imgHinh);

        //Gán giá trị vào từng item
        TraiCay traiCay = traiCays.get(position);
        txtName.setText(traiCay.getName());
        txtNote.setText(traiCay.getNote());
        imgHinh.setImageResource(traiCay.getImage());

        //Tạo và set Animation
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_for_list);
        convertView.startAnimation(animation);

        return convertView;
    }
}
