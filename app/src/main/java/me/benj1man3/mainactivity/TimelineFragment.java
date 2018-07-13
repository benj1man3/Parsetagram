package me.benj1man3.mainactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import me.benj1man3.mainactivity.model.Post;

public class TimelineFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPost;

    private Button refreshButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_timeline, parent, false);
    }


    //TODO- Finish the adapter and posts


    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        refreshButton = view.findViewById(R.id.btnRefresh);

        rvPost = view.findViewById(R.id.rvPost);

        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);
        rvPost.setAdapter(postAdapter);
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });
    }


    private void loadTopPosts(){
        final Post.Query postsQuery=new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e == null){
                    for(int i=0;i<objects.size();i++){
                        Log.d("HomeActivity", "Post[" + i + "] = "
                                + objects.get(i).getDescription()
                                + "\nusername=" +objects.get(i).getUser().getUsername()
                        );

                        posts.clear();
                        posts.addAll(objects);
                        postAdapter.notifyDataSetChanged();


                    }
                }else{
                    e.printStackTrace();
                }
            }
        });
    }



}