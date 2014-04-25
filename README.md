android-navigation-view
===================

## Overview

In app navigation using drawers has become a standard way to navigate through applications, and there are a lot of libraries that help you build the drawer layout experience, although I didn't found any library that would help the developer to build the navigation view itself. 

There is always a need to create a listview, an adapter to it, a class to store the data about the menu item and that's why I've created this library. To facilitate how to easily implement a menu to your app allowing you to do any customization to layouts or list view while decreasing the developer work by A LOT.

## Usage

Start by adding a NavigationView to into a drawer layout:

    <mazoni.navigationview.NavigationView
        android:id="@+id/navigation_view"
        android:layout_gravity="start"
        android:choiceMode="singleChoice"
        android:divider="@android:color/transparent"
        android:background="@android:color/darker_gray"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    
Then get the view and build your menu using the builder within MenuLayout:

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        NavigationView.Builder navigationBuilder = navigationView.getBuilder();
        NavigationItem.Listener listener = new NavigationItem.Listener() {...};
        NavigationItem.Listener specialListener = new NavigationItem.Listener() {...};
        
        navigationBuilder.inform(listener).
                addItem("Category 1").asSection().
                addItem("Item 1").
                addItem("item with icon").withIcon(R.drawable.icon_star).tagged("with-icon").
                addItem("Special Item").inform(specialListener).
                addItem("Category 2").asSection().
                addItem("item with icon").withIcon(R.drawable.icon_star).
                addItem("Item 1").
                addItem("Item 2").
                create();


## Example Result

![Example](https://raw.githubusercontent.com/mazoni/android-navigation-view/master/result.png "Result Example")
