package mazoni.navigationview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NavigationItemAdapter extends ArrayAdapter<NavigationItem> {

    private LayoutInflater layoutInflater;

    public NavigationItemAdapter(Context context, int resource) {
        super(context, resource);
        setup();
    }

    public NavigationItemAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        setup();
    }

    public NavigationItemAdapter(Context context, int resource, NavigationItem[] objects) {
        super(context, resource, objects);
        setup();
    }

    public NavigationItemAdapter(Context context, int resource, int textViewResourceId, NavigationItem[] objects) {
        super(context, resource, textViewResourceId, objects);
        setup();
    }

    public NavigationItemAdapter(Context context, int resource, List<NavigationItem> objects) {
        super(context, resource, objects);
        setup();
    }

    public NavigationItemAdapter(Context context, int resource, int textViewResourceId, List<NavigationItem> objects) {
        super(context, resource, textViewResourceId, objects);
        setup();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavigationItem item = getItem(position);

        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(item.getLayout(), null);
            holder = new ViewHolder();
            holder.label = (TextView) convertView.findViewById(R.id.label);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.label.setText(item.getLabel());
        if(item.getIcon() != null) {
            holder.icon.setImageDrawable(item.getIcon());
            holder.icon.setVisibility(View.VISIBLE);
        } else if(holder.icon != null) {
            holder.icon.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private void setup() {
        layoutInflater = getLayoutInflater();
    }

    private LayoutInflater getLayoutInflater() {
        return (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        TextView label;
        ImageView icon;
    }
}
