package me.benj1man3.mainactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import me.benj1man3.mainactivity.model.Post;

public class CameraFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    ImageView ivPhoto;
    Button postButton;
    private EditText descriptionInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.fragment_camera, parent, false);
        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


        descriptionInput = view.findViewById(R.id.etDescription);
        ivPhoto = view.findViewById(R.id.ivPreview);

        postButton= view.findViewById(R.id.btnCreate);

        //final ParseFile parseFile=

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String description=descriptionInput.getText().toString();
                final ParseUser user = ParseUser.getCurrentUser();

                //final File file=new File(imagePath);
                //final ParseFile parseFile=new ParseFile(file);

                createPost(description, user);
            }
        });
    }


    private void createPost(String description,  ParseUser user){
        final Post newPost= new Post();
        newPost.setDescription(description);
        //newPost.setImage(imageFile);
        newPost.setUser(user);

        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    Log.d("HomeActivity", "Create post success!");
                    System.out.println(newPost.getDescription().toString());
                }else{
                    e.printStackTrace();
                }
            }
        });
    }
}
