package me.benj1man3.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.parse.ParseUser;

public class ProfileFragment extends Fragment {
    private ImageButton logoutButton;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_profile, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        logoutButton =view.findViewById(R.id.outButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("PLEASE LOGOUT");
                ParseUser user=ParseUser.getCurrentUser();
                ParseUser.logOut();

                ParseUser currentUser = ParseUser.getCurrentUser();

                Intent intent= new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
