android-menu-layout
===================

## Overview

In app navigation using drawers has become a standard way to navigate through applications, and there are a lot of libraries that help you build the drawer layout experience, although I didn't found any library that would help the developer to build the menu itself. 

There is always a need to create a listview, an adapter to it, a class to store the data about the menu item and that's why I've created this library. To facilitate how to easily implement a menu to your app allowing you to do any customization to layouts or list view while decreasing the developer work by A LOT.

## Usage

Start by adding a MenuLayout to the xml you pretend to use in your drawer layout:

    <mazoni.menulayout.MenuLayout
        android:id="@+id/menu_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    
Then get the view and build your menu using the builder within MenuLayout:

    MenuLayout menuLayout = (MenuLayout) inflatedView.findViewById(R.id.menu_layout);
    MenuLayout.Builder menuLayoutBuilder = menuLayout.getBuilder();
    mazoni.menulayout.MenuItem.Listener listener = new mazoni.menulayout.MenuItem.Listener() {...};
    mazoni.menulayout.MenuItem.Listener specialListener = new mazoni.menulayout.MenuItem.Listener() {...};
    menuLayoutBuilder.inform(listener).
            addItem("Category 1").asSection().
            addItem("Item 1").
            addItem("item with icon").withIcon(R.drawable.icon_star).taggedWith("with-icon").
            addItem("Special Item").inform(specialListener).
            addItem("Category 2").asSection().
            addItem("item with icon").withIcon(R.drawable.icon_star).
            addItem("Item 1").
            addItem("Item 2").
            create();

## Example Result

![Example](https://raw.githubusercontent.com/mazoni/android-menu-layout/master/result.png "Result Example")
