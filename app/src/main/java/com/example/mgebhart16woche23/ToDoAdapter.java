package com.example.mgebhart16woche23;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ToDoAdapter extends BaseAdapter {

    private List<Note> noteList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ToDoAdapter(Context context, List<Note> noteList) {
        this.noteList = noteList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItems holder;
        if(convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.listview_layout,null);
            holder = new ViewHolderItems();
            holder.dateView = (TextView) convertView.findViewById(R.id.textDate);
            holder.noteView = (TextView) convertView.findViewById(R.id.textNote);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxChecked);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolderItems) convertView.getTag();
        }

        Note note = this.noteList.get(position);
        holder.dateView.setText(note.getDateTime());
        holder.noteView.setText(note.getNote());

        return convertView;
    }
}
