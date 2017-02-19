package com.salemwika.andrade.salemwika;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Share extends Fragment {

    private Button shareBtn;

    public Share() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_share, container, false);
        getActivity().setTitle("Share this App");

        shareBtn = (Button) v.findViewById(R.id.btnShare);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Salem Wika");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "English-Filipino Pharmacologic Translator");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        return v;
    }

}
