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
public class Favorites extends Fragment {

    private ListView lvFave;
    private ArrayList<String> listItem = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    public Favorites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        getActivity().setTitle("Favorites");
        Database db = new Database(getActivity());
        lvFave = (ListView) v.findViewById(R.id.lvFavorites);

        Cursor res = db.getAllFave();
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, listItem);
        while(res.moveToNext()){
            listItem.add(res.getString(1)+"\t-\t"+res.getString(2));
            adapter.notifyDataSetChanged();
        }

        lvFave.setAdapter(adapter);
        lvFave.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), listItem.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
