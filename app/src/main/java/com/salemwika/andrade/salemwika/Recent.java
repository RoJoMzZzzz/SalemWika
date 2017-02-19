package com.salemwika.andrade.salemwika;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Recent extends Fragment {

    private ListView lvRec;
    private ArrayList<String> listItem = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    public Recent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        getActivity().setTitle("Recent");

        Database db = new Database(getActivity());
        lvRec = (ListView) v.findViewById(R.id.lvRecent);

        Cursor res = db.getAllRecent();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listItem);

        while(res.moveToNext()){
            listItem.add(res.getString(1)+"\t-\t"+res.getString(2));
            adapter.notifyDataSetChanged();
        }

        lvRec.setAdapter(adapter);
        lvRec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), listItem.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
