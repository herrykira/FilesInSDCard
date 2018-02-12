package com.example.kinhangpoon.filesinsdcard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kinhangpoon.filesinsdcard.R;

import java.util.LinkedList;

/**
 * Created by KinhangPoon on 10/2/2018.
 */

public class FrequentAdapter extends BaseAdapter {
    LinkedList<String> fileNames;
    LinkedList<Integer> frequency;
    Context cts;
    LayoutInflater inflater;

    public FrequentAdapter(LinkedList<String> fileNames, LinkedList<Integer> frequency, Context cts) {
        this.fileNames = fileNames;
        this.frequency = frequency;
        this.cts = cts;
        inflater = (LayoutInflater) cts.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fileNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView filename;
        TextView filefrequency;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item1,null);
            holder.filename = convertView.findViewById(R.id.textViewFrequent);
            holder.filefrequency = convertView.findViewById(R.id.textViewTimes);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        holder.filename.setText("File Extension: "+fileNames.get(position));
        holder.filefrequency.setText("Frequency: "+frequency.get(position));
        return convertView;
    }
}
