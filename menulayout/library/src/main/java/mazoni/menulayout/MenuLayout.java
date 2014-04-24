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

        protected Builder() {
            items = new ArrayList<MenuItem>();
        }

        /**
         * Adds an item specifying every possible detail about it
         * @param icon
         * @param label
         * @param tag
         * @param layout this layout should contains one @id/label and one @id/icon
         * @param listener
         * @return
         */
        public Builder addMenuItem(int icon, String label, String tag, int layout, MenuItem.Listener listener) {
            Drawable iconDrawable = icon == 0? null : getResources().getDrawable(icon);
            MenuItem menuItem = new MenuItem(iconDrawable, label, tag, layout);
            menuItem.setListener(listener);
            items.add(menuItem);
            lastItem = menuItem;
            return this;
        }

        /**
         * Adds a menu item using the default layout, which is R.layout.menu_item of this library
         * @param icon
         * @param label
         * @param tag
         * @param listener
         * @return
         */
        public Builder addMenuItem(int icon, String label, String tag, MenuItem.Listener listener) {
            addMenuItem(icon, label, tag, R.layout.menu_item, listener);
            return this;
        }

        /**
         * Adds a menu item using the array position as tag and no icon
         * @param label
         * @param listener
         * @return
         */
        public Builder addMenuItem(String label, MenuItem.Listener listener) {
            addMenuItem(0, label, String.valueOf(items.size()), R.layout.menu_item, listener);
            return this;
        }

        /**
         * Sets a listener to the last item added to the builder
         * @param listener
         * @return
         */
        public Builder setListener(MenuItem.Listener listener) {
            lastItem.setListener(listener);
            return this;
        }

        /**
         * Sets the layout to the default R.layout.section_item
         * @return
         */
        public Builder setAsSection() {
            lastItem.setLayout(R.layout.section_item);
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
