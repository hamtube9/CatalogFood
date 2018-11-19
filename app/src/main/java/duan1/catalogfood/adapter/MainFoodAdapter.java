package duan1.catalogfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import duan1.catalogfood.R;


import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.MainFood;
import duan1.catalogfood.model.MainFoodDAO;

public class MainFoodAdapter extends BaseAdapter implements Filterable {
    List<MainFood> mainFoodList;
    List<MainFood> listSort;
    private Filter MFfilter;
    private Activity context;
    private MainFoodDAO mfDao;
    private final LayoutInflater layoutInflater;

    public MainFoodAdapter( Activity context, List<MainFood> mainFoodList){
        super();
        this.context=context;
        this.listSort=mainFoodList;
        this.mainFoodList=mainFoodList;
        this.layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mfDao=new MainFoodDAO(context);
    }


    @Override
    public int getCount() {
        return mainFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return mainFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_list_mainfood,parent,false);
            holder.imgMainFood=convertView.findViewById(R.id.imgMainFood);
            holder.tvName = convertView.findViewById(R.id.tvTenMainFood);
            holder.tvAddress = convertView.findViewById(R.id.tvDiaChiMainFood);
            holder.tvPhone = convertView.findViewById(R.id.tvDTMainFood);
            holder.tvPrice = convertView.findViewById(R.id.tvGiaMainFood);
            convertView.setTag(holder);
        }else
            holder = (MainFoodAdapter.ViewHolder) convertView.getTag();

            MainFood mf = mainFoodList.get(position);
            holder.tvName.setText(mf.getName());
            holder.tvAddress.setText(mf.getDiachi());
            holder.tvPhone.setText(mf.getDienthoai());
            holder.tvPrice.setText(mf.getGia());

        byte[] img = mainFoodList.get(position).getAnh();
        Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.imgMainFood.setImageBitmap(imgBitmap);
        return convertView;



    }

    @Override
    public Filter getFilter() {

        return null;
    }
    public static class ViewHolder {
        TextView tvName, tvAddress, tvPrice, tvPhone;
        ImageView imgMainFood;
    }
    public void changeDataset(List<MainFood> items) {
        this.mainFoodList = items;
        notifyDataSetChanged();
    }
}
