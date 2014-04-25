package mazoni.navigationview;

import android.graphics.drawable.Drawable;

public class NavigationItem {

    private Drawable icon;
    private String label;
    private String tag;
    private int layout;
    private Listener listener;

    public NavigationItem(Drawable icon, String label, String tag, int layout) {
        this.icon = icon;
        this.label = label;
        this.tag = tag;
        this.layout = layout;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public static interface Listener {
        void onItemClick(NavigationItem navigationItem);
        void onItemLongClick(NavigationItem navigationItem);
    }

    public static abstract class ListenerAdapter implements Listener {
        @Override
        public void onItemClick(NavigationItem navigationItem) {}

        @Override
        public void onItemLongClick(NavigationItem navigationItem) {}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NavigationItem navigationItem = (NavigationItem) o;

        if (icon != null ? !icon.equals(navigationItem.icon) : navigationItem.icon != null) return false;
        if (label != null ? !label.equals(navigationItem.label) : navigationItem.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = icon != null ? icon.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
