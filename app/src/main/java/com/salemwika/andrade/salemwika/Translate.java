package com.salemwika.andrade.salemwika;


import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Translate extends Fragment {

    private Button trans, clear, fab;
    private RadioButton ef, fe;
    private AutoCompleteTextView one;
    private TextView two;
    private ArrayAdapter adapter;

    public Translate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_translate, container, false);
        getActivity().setTitle("SalemWika");

        fab = (Button) v.findViewById(R.id.fab);

        ef = (RadioButton) v.findViewById(R.id.rdoEF);
        fe = (RadioButton) v.findViewById(R.id.rdoFE);
        one = (AutoCompleteTextView) v.findViewById(R.id.edt1);
        two = (TextView) v.findViewById(R.id.edt2);
        trans = (Button) v.findViewById(R.id.btnTranslate);
        clear = (Button) v.findViewById(R.id.btnClear);

        final Database db = new Database(getActivity());

        Resources res = getResources();
        final String[] eng = res.getStringArray(R.array.english);
        final String[] fil = res.getStringArray(R.array.fil);

        if (ef.isChecked()) {
            adapter = new ArrayAdapter(getActivity(),android.R.layout.select_dialog_item, eng);
            one.setThreshold(1);
            one.setAdapter(adapter);
        } else if(fe.isChecked()){
            adapter = new ArrayAdapter(getActivity(),android.R.layout.select_dialog_item, fil);
            one.setThreshold(1);
            one.setAdapter(adapter);
        }

       ef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter = new ArrayAdapter(getActivity(),android.R.layout.select_dialog_item, eng);
                    one.setThreshold(1);
                    one.setAdapter(adapter);
                    one.getText().clear();
                    two.setText("");
                }
            }
        });

        fe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter = new ArrayAdapter(getActivity(),android.R.layout.select_dialog_item, fil);
                    one.setThreshold(1);
                    one.setAdapter(adapter);
                    one.getText().clear();
                    two.setText("");
                }
            }
        });




        trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ef.isChecked()==false)&&(fe.isChecked()==false)) {
                    Toast.makeText(getActivity(),"Please select translation type",Toast.LENGTH_SHORT).show();
                } else if (one.getText().toString().trim().length()==0) {
                    one.setError("This can't be empty!");
                } else {
                    String toBe = one.getText().toString();

                    if(ef.isChecked()){
                        for(int i = 0; i<eng.length; i++){
                            if(toBe.equalsIgnoreCase(eng[i])){
                                two.setText(fil[i]);
                            }
                        }

                        if(two.getText().toString().equalsIgnoreCase(""))
                            two.setText("Not Found!!");

                    } else {
                        for(int i = 0; i<eng.length; i++){
                            if(toBe.equalsIgnoreCase(fil[i])){
                                two.setText(eng[i]);
                            }
                        } if(two.getText().toString().equalsIgnoreCase(""))
                            two.setText("Not Found!!");
                    }

                    if (!(two.getText().toString().equalsIgnoreCase("Not Found!!"))) {
                        boolean ins = db.insertRecent(toBe, two.getText().toString());
                        if(ins)
                            Toast.makeText(getActivity(),"inserted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getActivity(),"not inserted",Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.getText().clear();
                two.setText("");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((ef.isChecked()==false)&&(fe.isChecked()==false)) {
                    Toast.makeText(getActivity(),"Please select translation type",Toast.LENGTH_SHORT).show();
                } else if (one.getText().toString().trim().length()==0) {
                    one.setError("This can't be empty!");
                }else if (two.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(),"Translate it first",Toast.LENGTH_SHORT).show();
                }  else if (two.getText().toString().equalsIgnoreCase("Not Found!!")) {
                    Toast.makeText(getActivity(),"Can't add to your favorites",Toast.LENGTH_SHORT).show();
                } else {

                    final String toBe = one.getText().toString();

                    AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
                    ab.setMessage("Do you want to add \""+toBe+"\" in your favorites?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean ins = db.insertFave(toBe, two.getText().toString());
                                    if(ins)
                                        Toast.makeText(getActivity(),"inserted",Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(getActivity(),"not inserted",Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog ad = ab.create();
                    ad.setTitle("CONFIRMATION MESSAGE");
                    ad.show();

                }
            }
        });

        return v;
    }

}
