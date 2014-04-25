package mazoni.navigationview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that should be add to the layout where the menu is supposed to appear
 */
public class NavigationView extends ListView {

    private Builder builder;

    public NavigationView(Context context) {
        super(context);
        init();
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Builder getBuilder() {
        return builder;
    }

    private void init() {
        builder = new Builder();
    }

    public class Builder {
        private List<NavigationItem> items;
        private NavigationItem lastItem;
        private int sectionLayout = R.layout.section_item;
        private int itemLayout = R.layout.navigation_item;
        private NavigationItem.Listener defaultListener = null;

        protected Builder() {
            items = new ArrayList<NavigationItem>();
        }

        /**
         * Uses the layout in argument as default layout for sections
         * @param sectionLayout
         * @return
         */
        public Builder useSectionLayout(int sectionLayout) {
            this.sectionLayout = sectionLayout;
            return this;
        }

        /**
         * Uses the layout in argument as default layout for items
         * @param itemLayout
         * @return
         */
        public Builder useItemLayout(int itemLayout) {
            this.itemLayout = itemLayout;
            return this;
        }

        /**
         * Adds a menu item using the array position as tag and no icon
         * @param label
         * @return
         */
        public Builder addItem(String label) {
            addItem(0, label, String.valueOf(items.size()), R.layout.navigation_item, defaultListener);
            return this;
        }

        /**
         * Loads the stringResource from strings and add an item using it
         * @param stringResource
         * @return
         */
        public Builder addItem(int stringResource) {
            String label = getResources().getString(stringResource);
            addItem(0, label, String.valueOf(items.size()), R.layout.navigation_item, defaultListener);
            return this;
        }

        /**
         * Sets the last added item to use section layout
         * @return
         */
        public Builder asSection() {
            lastItem.setLayout(this.sectionLayout);
            return this;
        }

        /**
         * Uses the icon in argument in the last inserted item
         * @param icon
         * @return
         */
        public Builder withIcon(int icon) {
            Drawable iconDrawable = icon == 0? null : getResources().getDrawable(icon);
            return withIcon(iconDrawable);
        }

        /**
         * Uses the icon in argument in the last inserted item
         * @param icon
         * @return
         */
        public Builder withIcon(Drawable icon) {
            lastItem.setIcon(icon);
            return this;
        }

        /**
         * Tags the last added item with the argument tag
         * @param tag
         * @return
         */
        public Builder tagged(String tag) {
            lastItem.setTag(tag);
            return this;
        }

        /**
         * Uses the layout argument as a custom layout to the last added item
         * @param layout
         * @return
         */
        public Builder inflatedInto(int layout) {
            lastItem.setLayout(layout);
            return this;
        }

        /**
         * If no item was added, uses the argument listener as default listener
         * If an item was added, uses the argument listener as listener only of the last item
         * @param listener
         * @return
         */
        public Builder inform(NavigationItem.Listener listener) {
            if(lastItem != null) lastItem.setListener(listener);
            else defaultListener = listener;
            return this;
        }

        /**
         * Defines the last added item as notClickable
         */
        public Builder notClickable() {
            if(lastItem != null) lastItem.setListener(null);
            return this;
        }




        private Builder addItem(int icon, String label, String tag, int layout, NavigationItem.Listener listener) {
            Drawable iconDrawable = icon == 0? null : getResources().getDrawable(icon);
            NavigationItem navigationItem = new NavigationItem(iconDrawable, label, tag, layout);
            if(listener != null) navigationItem.setListener(listener);
            items.add(navigationItem);
            lastItem = navigationItem;
            return this;
        }

        /**
         * Creates an adapter using the items added so far
         */
        public void create() {
            NavigationItemAdapter navigationItemAdapter = new NavigationItemAdapter(getContext(), 0, items);
            NavigationView.this.setAdapter(navigationItemAdapter);
            setListeners(NavigationView.this);
        }

        private void setListeners(NavigationView navigationView) {
            setOnClickListener(navigationView);
            setOnLongClickListener(navigationView);
        }

        private void setOnLongClickListener(NavigationView navigationView) {
            NavigationView.this.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    NavigationItem item = (NavigationItem) view.getTag();
                    if(item.getListener() != null) {
                        item.getListener().onItemClick(item);
                        return true;
                    }
                    return false;
                }
            });
        }

        private void setOnClickListener(final NavigationView navigationView) {
            NavigationView.this.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NavigationItem item = (NavigationItem) NavigationView.this.getAdapter().getItem(position);
                    NavigationView.this.setSelection(position);
                    if(item.getListener() != null) {
                        item.getListener().onItemClick(item);
                    }
                }
            });
        }
    }
}
