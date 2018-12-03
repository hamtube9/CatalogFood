package duan1.catalogfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import duan1.catalogfood.ChangeInfoFastFoodActivity;
import duan1.catalogfood.FastFoodActivity;
import duan1.catalogfood.R;
import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.FastFoodDAO;

public class FastFoodAdapter extends BaseAdapter implements Filterable {
    List<FastFood> FFList;
    List<FastFood> listSort;
    private Filter FFfilter;
    private Activity context;
    private FastFoodDAO FFDao;

    private final LayoutInflater inflater;



    public FastFoodAdapter(Activity context, List<FastFood> FFList) {
        super();
        this.FFList = FFList;
        this.listSort = FFList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        FFDao = new FastFoodDAO(context);
    }

    @Override
    public int getCount() {
        return FFList.size();
    }

    @Override
    public Object getItem(int i) {
        return FFList.get(i);

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_list_fastfood,viewGroup,false);
            holder.imgFastFood=view.findViewById(R.id.imgFastFood);
            holder.tvName = view.findViewById(R.id.tvTenFastFood);
            holder.tvAddress = view.findViewById(R.id.tvDiaChiFastFood);
            holder.tvPhone = view.findViewById(R.id.tvDTFastFood);
            holder.tvPrice = view.findViewById(R.id.tvGiaFastFood);
            view.setTag(holder);




        }else
            holder = (ViewHolder) view.getTag();

            FastFood ffood = FFList.get(position);
            holder.tvName.setText(ffood.getName());
            holder.tvAddress.setText(ffood.getDiachi());
            holder.tvPhone.setText(ffood.getDienthoai());
            holder.tvPrice.setText(ffood.getGia());

            byte[] img = FFList.get(position).getAnh();
            Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imgFastFood.setImageBitmap(imgBitmap);
            return view;



    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        TextView tvName, tvAddress, tvPrice, tvPhone;
        ImageView imgFastFood;
    }
    public void changeDataset(List<FastFood> items) {
        this.FFList = items;
        notifyDataSetChanged();
    }
}
