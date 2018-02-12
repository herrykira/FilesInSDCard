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

public class BigAdapter extends BaseAdapter {
    LinkedList<String> fileNames;
    LinkedList<String> fileSizes;
    Context cts;
    LayoutInflater inflater;

    public BigAdapter(LinkedList<String> fileNames, LinkedList<String> fileSizes,Context cts) {
        this.fileNames = fileNames;
        this.fileSizes = fileSizes;
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

    public class ViewHolder{
        TextView name;
        TextView size;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = new ViewHolder();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item,null);
            viewholder.name = convertView.findViewById(R.id.textViewFileName);
            viewholder.size = convertView.findViewById(R.id.textViewSize);
            convertView.setTag(viewholder);
        }
        else{
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.name.setText("File Name: "+fileNames.get(position));
        viewholder.size.setText("File Size: "+fileSizes.get(position)+" KB");

        return convertView;
    }
}
