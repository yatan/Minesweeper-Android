package udl.eps.frb2.buscaminas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by yatan on 4/19/16.
 */
public class CustomAdapter extends BaseAdapter {
    private ArrayList<Button> aButtons = null;

    public CustomAdapter(ArrayList<Button> b)
    {
        aButtons = b;
    }
    @Override
    public int getCount(){
        return aButtons.size();
    }
    @Override
    public Object getItem(int position){
        return (Object) aButtons.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Button button;
        if(convertView == null){
            button = aButtons.get(position);
        }
        else{
            button = (Button) convertView;
        }
        return button;
    }
}
