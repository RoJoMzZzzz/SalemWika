package com.salemwika.andrade.salemwika;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Rate extends Fragment {

    private RatingBar ratingBar;
    private TextView rateTxt;
    private EditText feedEdt;
    private Button sendFeedBtn;

    public Rate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rate, container, false);
        getActivity().setTitle("Rate and Feedback");

        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        rateTxt = (TextView) v.findViewById(R.id.txtRating);
        feedEdt = (EditText) v.findViewById(R.id.edtFeedback);
        sendFeedBtn = (Button) v.findViewById(R.id.btnSendRate);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateTxt.setText(""+rating+" stars");
            }
        });

        sendFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ratingBar.getRating() == 0){
                    Toast.makeText(getActivity(),"Please rate first",Toast.LENGTH_SHORT).show();
                } else if(feedEdt.getText().toString().trim().length() == 0){
                    feedEdt.setError("This item can't be empty!");
                } else {
                    String rateFeed = "Rate: "+ratingBar.getRating()+" stars\n"+"Feedback: "+feedEdt.getText().toString();
                    Toast.makeText(getActivity(),rateFeed,Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

}
