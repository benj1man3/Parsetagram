package me.benj1man3.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import me.benj1man3.mainactivity.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    public List<Post> mPosts;
    Context context;

    public PostAdapter(List<Post> posts){
        mPosts=posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView=inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Post post=mPosts.get(position);

        viewHolder.tvUsername.setText(post.getUser().getUsername());
        viewHolder.tvBody.setText(post.getDescription());
        //viewHolder.tvTime.setText(getRelativeTimeAgo(post.getCreatedAt()));

        ParseFile picture = post.getImage();
        if (picture != null) {
            Glide.with(context).load(picture.getUrl()).into(viewHolder.ivProfileImage);
        } else {
            viewHolder.ivProfileImage.setImageResource(R.drawable.instagram_name);
        }

        //add the time to the post
        viewHolder.tvTime.setText(getRelativeTimeAgo(post.getCreatedAtString()));


    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String postFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(postFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            //perform findViewById lookups


            //TODO - set the images, username, and body text
            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }
}
