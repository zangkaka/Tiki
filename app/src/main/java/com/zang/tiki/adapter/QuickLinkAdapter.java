package com.zang.tiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.zang.tiki.R;
import com.zang.tiki.model.QuickLink;

import java.util.Collections;
import java.util.List;

/**
 * Created by Rang on 19-Aug-20.
 */
public class QuickLinkAdapter extends RecyclerView.Adapter<QuickLinkAdapter.QuickLinkViewHolder> {

    List<QuickLink> list;
    Context context;

    public QuickLinkAdapter(List<QuickLink> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void addData(List<QuickLink> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuickLinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new QuickLinkViewHolder(inflater.inflate(R.layout.item_quick_link, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuickLinkViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class QuickLinkViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        public QuickLinkViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_quick_link_txt);
            image = itemView.findViewById(R.id.item_quick_link_img);
        }
    }
}
