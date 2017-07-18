package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.streetshopes.R;

import java.util.ArrayList;

/**
 * Created by hardik on 7/12/16.
 */
public class DropDownAdapter extends BaseAdapter {
    private Activity mContext;
    ArrayList<String> nameList;
    private LayoutInflater mInflator;

    public DropDownAdapter(Activity activity, ArrayList<String> quesList) {
        this.mContext = activity;
        this.nameList = quesList;
        mInflator = LayoutInflater.from(mContext);


    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            view = mInflator.inflate(R.layout.row_list_item, null);
            holder = new ViewHolder();
            holder.mNameTv = (TextView) view.findViewById(R.id.row_value_tv);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.mNameTv.setText(nameList.get(position));

        return view;
    }

    class ViewHolder {


        private TextView mNameTv;

    }
}
