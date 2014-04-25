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

## Building
### Gradle

#### From sonatype

Add sonatype snapshot repository to your `build.gradle`:

```groovy
buildscript {
  repositories {
    maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
  }
}
 
repositories {
  maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
}
```

Then declare navigationview within your dependencies:

```groovy
dependencies {
  ...
  compile 'com.github.mazoni.navigationview:library:0.1-SNAPSHOT@aar'
  ...
}
```

License
=======

    Copyright 2013 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://github.com/mazoni/android-navigation-view/
