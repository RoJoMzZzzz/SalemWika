package com.salemwika.andrade.salemwika;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class List extends Fragment {

    private ListView lvList;
    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter adapter;
    private ArrayAdapter<String> adapter1;
    private Spinner spn;

    public List() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        getActivity().setTitle("List");
        lvList = (ListView) v.findViewById(R.id.lvList);
        spn = (Spinner) v.findViewById(R.id.spinner);

        Resources res = getResources();
        final String[] eng = res.getStringArray(R.array.english);
        final String[] fil = res.getStringArray(R.array.fil);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.find, android.R.layout.simple_spinner_item);
        adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listItems);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listItems.clear();
                for(int i = 0; i<eng.length; i++){

                    char chk = eng[i].charAt(0);
                    char spnItem = parent.getItemAtPosition(position).toString().charAt(0);
                    if(parent.getItemAtPosition(position).toString().equalsIgnoreCase("Show All")){
                        listItems.add(eng[i]+"\t-\t"+fil[i]);
                        adapter1.notifyDataSetChanged();
                    }
                    else if(chk == spnItem){
                        listItems.add(eng[i]+"\t-\t"+fil[i]);
                        adapter1.notifyDataSetChanged();
                    }
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lvList.setAdapter(adapter1);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), listItems.get(position), Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
