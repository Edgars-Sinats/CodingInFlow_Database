package eu.alfo.rtu_pit;

//import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
    Context context;
    String countryList;
    int flags;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String countryList, int flags) {
        this.context = applicationContext;
        this.countryList = countryList;
        this.flags = flags;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return 51;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


//  public View getView(int position, View view, ViewGroup viewGroup) {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_list_view_question_plus, null);
        TextView country = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        country.setText(countryList);
        icon.setImageResource(flags);
        return view;
    }
}

