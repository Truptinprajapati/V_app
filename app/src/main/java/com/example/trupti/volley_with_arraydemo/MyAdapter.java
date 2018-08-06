package com.example.trupti.volley_with_arraydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lenovo on 05-08-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    List<Iteamlist> iteamlists;
    Context context;

    public MyAdapter(List<Iteamlist> iteamlistsa, Context applicationContext) {
        this.context=applicationContext;
        this.iteamlists=iteamlistsa;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.datalist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Iteamlist listiteam=iteamlists.get(position);
        holder.name.setText(listiteam.getName());
        holder.link.setText(listiteam.getLink());
        Picasso.with(context).load(listiteam.getImage()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return iteamlists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,link;
        ImageView iv;

    public ViewHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name);
        link=(TextView)itemView.findViewById(R.id.link);
        iv=(ImageView)itemView.findViewById(R.id.image);

    }
}
}
