package com.example.sshahini.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sshahini.myapplication.MyApplication;
import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.datamodel.Cloth;

import java.util.List;

/**
 * Created by Saeed shahini on 7/30/2016.
 */
public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {

    private Context context;
    private List<Cloth> clothes;

    public ClothesAdapter(Context context, List<Cloth> clothes){
        this.context = context;
        this.clothes = clothes;
    }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_clothing_item,parent,false);

        return new ClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClothesViewHolder holder, int position) {
        Cloth cloth=clothes.get(position);

        holder.clothImage.setImageDrawable(cloth.getImage());
        holder.clothTitle.setText(cloth.getTitle());
        holder.clothTitle.setTypeface(MyApplication.getIranianSansFont(context));
        holder.clothViewCount.setText(String.valueOf(cloth.getViewCount()));
    }

    @Override
    public int getItemCount() {
        return clothes.size();
    }

    public class ClothesViewHolder extends RecyclerView.ViewHolder{
        private ImageView clothImage;
        private TextView clothTitle;
        private TextView clothViewCount;
        public ClothesViewHolder(View itemView) {
            super(itemView);
            clothImage=(ImageView)itemView.findViewById(R.id.clothing_image);
            clothTitle=(TextView) itemView.findViewById(R.id.clothing_title);
            clothViewCount=(TextView)itemView.findViewById(R.id.clothing_viewcount_text);
        }
    }
}
