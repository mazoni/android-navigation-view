package mazoni.menulayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that should be add to the layout where the menu is supposed to appear
 */
public class MenuLayout extends LinearLayout {

    private ListView itemsListView;
    private Builder builder;

    public MenuLayout(Context context) {
        super(context);
        init();
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MenuLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Builder getBuilder() {
        return builder;
    }

    public ListView getItemsListView() {
        return this.itemsListView;
    }


    private void init() {
        itemsListView = new ListView(getContext());
        this.addView(itemsListView);
        builder = new Builder();
    }

    public class Builder {
        private List<MenuItem> items;
        private MenuItem lastItem;
        private int sectionLayout = R.layout.section_item;
        private int itemLayout = R.layout.menu_item;
        private MenuItem.Listener defaultListener = null;

        protected Builder() {
            items = new ArrayList<MenuItem>();
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
            addItem(0, label, String.valueOf(items.size()), R.layout.menu_item, defaultListener);
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
        public Builder taggedWith(String tag) {
            lastItem.setTag(tag);
            return this;
        }

        /**
         * Uses the layout argument as a custom layout to the last added item
         * @param layout
         * @return
         */
        public Builder intoLayout(int layout) {
            lastItem.setLayout(layout);
            return this;
        }

        /**
         * If no item was added, uses the argument listener as default listener
         * If an item was added, uses the argument listener as listener only of the last item
         * @param listener
         * @return
         */
        public Builder inform(MenuItem.Listener listener) {
            if(lastItem != null) lastItem.setListener(listener);
            else defaultListener = listener;
            return this;
        }

        private Builder addItem(int icon, String label, String tag, int layout, MenuItem.Listener listener) {
            Drawable iconDrawable = icon == 0? null : getResources().getDrawable(icon);
            MenuItem menuItem = new MenuItem(iconDrawable, label, tag, layout);
            menuItem.setListener(listener);
            items.add(menuItem);
            lastItem = menuItem;
            return this;
        }

        /**
         * Creates an adapter using the items added so far
         */
        public void create() {
            MenuItemAdapter menuItemAdapter = new MenuItemAdapter(getContext(), 0, items);
            MenuLayout.this.itemsListView.setAdapter(menuItemAdapter);
            setListeners(MenuLayout.this);
        }

        private void setListeners(MenuLayout menuLayout) {
            setOnClickListener(menuLayout);
            setOnLongClickListener(menuLayout);
        }

        private void setOnLongClickListener(MenuLayout menuLayout) {
            menuLayout.itemsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    MenuItem item = (MenuItem) view.getTag();
                    if(item.getListener() != null) {
                        item.getListener().onClick(item);
                        return true;
                    }
                    return false;
                }
            });
        }

        private void setOnClickListener(final MenuLayout menuLayout) {
            menuLayout.itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MenuItem item = (MenuItem) menuLayout.itemsListView.getAdapter().getItem(position);
                    menuLayout.itemsListView.setSelection(position);
                    if(item.getListener() != null) {
                        item.getListener().onClick(item);
                    }
                }
            });
        }
    }
}
