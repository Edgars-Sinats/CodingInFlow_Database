package eu.alfo.rtu_pit.db;

//import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import eu.alfo.rtu_pit.R;

public class CustomAdapter extends BaseAdapter{


    private class ViewHolder{
        // add here views, which are defined in `list_row Layout`
        public TextView data;
        public TextView textBig;

    }

    String[] columnNames;
    LayoutInflater inflter;


    private List<ArrayList<String>> rows;

    public CustomAdapter(Context context, List<ArrayList<String>> rows, String[] columnNames) {
        this.columnNames = columnNames;
        this.rows = rows;

        this.inflter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


//  public View getView(int position, View view, ViewGroup viewGroup) {

    //    @Todo izveidot listu no DB priekš DebuginActivity
    @Override
    public View getView(int iPosition, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflter.inflate(R.layout.activity_list_view_row, null);
            holder = new ViewHolder();
            holder.data = convertView.findViewById(R.id.textViewDbInsert);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        StringBuilder stringBuilder = new StringBuilder();
        String itemText;

        String userSelected = "Teacher_ID";

        for(int i = 0; i < this.columnNames.length; i++){
            String t = this.columnNames[i] + ": " + rows.get(iPosition).get(i);



            stringBuilder.append(t);

            if(i != this.columnNames.length - 1){
                stringBuilder.append(", ");
            }

        }

        holder.data.setText(stringBuilder.toString());

        return convertView;
    }


}


