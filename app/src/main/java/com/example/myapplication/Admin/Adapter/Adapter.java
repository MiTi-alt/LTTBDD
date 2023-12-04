package com.example.myapplication.Admin.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.Model.News;
import com.example.myapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<News> {

    private Activity context;
    private List<News> listNews;

    public Adapter(Activity mainActivity, ArrayList<News> dataArrayList) {
        super(mainActivity, 0, dataArrayList);
        this.context = mainActivity;
        this.listNews = dataArrayList;
    }
    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    private static class ViewHolder {
        TextView title, description, url, time_create, author;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.admin_list_news, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.titleNews);
            holder.description = convertView.findViewById(R.id.description);
            holder.author = convertView.findViewById(R.id.author);
            holder.image = convertView.findViewById(R.id.image);
            holder.url = convertView.findViewById(R.id.url);
            holder.time_create = convertView.findViewById(R.id.time_create);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        News news = listNews.get(position);
        if (news != null) {
            holder.title.setText(news.getTitle());
            holder.description.setText(news.getAbstracts());
            holder.author.setText(news.getAuthor());
            //holder.url.setText(news.getUrl());
            holder.time_create.setText(news.getDateTIme());
            String imageUrl = news.getImage();
            Glide.with(context)
                    .load(imageUrl)
                    .apply(new RequestOptions().error(R.drawable.ic_baseline_email_24))
                    .into(holder.image);
            Log.d("API_RESULT", "Article: " + news.toString());
            holder.url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = news.getUrl();
                    openWebPage(url);
                }

                private void openWebPage(String url) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
