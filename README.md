android-menu-layout
===================

## Overview

In app navigation using drawers has become a standard way to navigate through applications, and there are a lot of libraries that help you build the drawer layout experience, although I didn't found any library that would help the developer to build the menu itself. We always need to create a listview, an adapter to it, a class to store the data about the menu item and that's why I've created this library. To facilitate how to easily implement a menu to your app allowing you to do any customization to layouts or list view while decreasing the developer work by A LOT.

## Usage

Start by adding a MenuLayout to the xml you pretend to use in your drawer layout:

    <mazoni.menulayout.MenuLayout
        android:id="@+id/menu_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    
Then get the view and build your menu using the builder within MenuLayout:

    MenuLayout menuLayout = (MenuLayout) inflatedView.findViewById(R.id.menu_layout);
        MenuLayout.Builder menuLayoutBuilder = menuLayout.getBuilder();
        mazoni.menulayout.MenuItem.Listener listener = new mazoni.menulayout.MenuItem.Listener() {
            @Override
            public void onClick(mazoni.menulayout.MenuItem menuItem) {
                Log.d("MenuItemClicked", String.format("menuItem clicked %s",menuItem.getTag()));
            }

            @Override
            public void onLongClick(mazoni.menulayout.MenuItem menuItem) {
                Log.d("MenuItemLongClicked", String.format("menuItem long clicked %s",menuItem.getTag()));
            }
        };
        menuLayoutBuilder.
                addMenuItem("Category 1", listener).setAsSection().
                addMenuItem("Item 1", listener).
                addMenuItem(R.drawable.icon_star, "item with icon", "with-icon", listener).
                addMenuItem("Item 2", listener).
                addMenuItem("Category 2", listener).setAsSection().
                addMenuItem(R.drawable.icon_star, "item with icon", "with-icon", listener).
                addMenuItem("Item 1", listener).
                addMenuItem("Item 2", listener).
                create();


