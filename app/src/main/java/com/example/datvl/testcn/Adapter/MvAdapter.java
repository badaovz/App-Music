package com.example.datvl.testcn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datvl.testcn.Model.VideoYouTub;
import com.example.datvl.testcn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MvAdapter extends BaseAdapter{
    private Context context;
    private int layout;
    private List<VideoYouTub> listVideoYouTub;

    public MvAdapter(Context context, int layout, List<VideoYouTub> listVideoYouTub) {
        this.context = context;
        this.layout = layout;
        this.listVideoYouTub = listVideoYouTub;
    }

    @Override
    public int getCount() {
        return listVideoYouTub.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView thumnail;
        TextView tenmv, channel;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);

            viewHolder.thumnail = view.findViewById(R.id.ivthumbnail);
            viewHolder.tenmv    = view.findViewById(R.id.tvtenmv);
            viewHolder.channel  = view.findViewById(R.id.tvchannel);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        VideoYouTub videoYouTub = listVideoYouTub.get(i);
        viewHolder.tenmv.setText(videoYouTub.getTitle());
        viewHolder.channel.setText(videoYouTub.getChannel());
        Picasso.with(context).load(videoYouTub.getThumbnail()).into(viewHolder.thumnail);

        return view;
    }
}
