package mazoni.navigationview.example;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import mazoni.menulayout.example.R;
import mazoni.navigationview.NavigationItem;
import mazoni.navigationview.NavigationView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        NavigationView.Builder navigationBuilder = navigationView.getBuilder();
        NavigationItem.Listener listener = new NavigationItem.Listener() {
            @Override
            public void onItemClick(NavigationItem menuItem) {
                Log.d("MenuItemClicked", String.format("menuItem clicked %s",menuItem.getTag()));
            }

            @Override
            public void onItemLongClick(NavigationItem menuItem) {
                Log.d("MenuItemLongClicked", String.format("menuItem long clicked %s",menuItem.getTag()));
            }
        };
        NavigationItem.Listener specialListener = new NavigationItem.Listener() {
            @Override
            public void onItemClick(NavigationItem menuItem) {
                Log.d("SpecialItemClicked", String.format("menuItem clicked %s",menuItem.getTag()));
            }

            @Override
            public void onItemLongClick(NavigationItem menuItem) {
                Log.d("SpecialItemLongClicked", String.format("menuItem long clicked %s",menuItem.getTag()));
            }
        };
        navigationBuilder.inform(listener).
                addItem("Section 1").asSection().
                addItem("Normal item 1").
                addItem("Custom layout").inflatedInto(R.layout.custom_layout).
                addItem("tagged item").tagged("tagged-item").
                addItem("Special Item").inform(specialListener).
                addItem("Section with icon").withIcon(R.drawable.icon_star).asSection().
                addItem("item with icon").withIcon(R.drawable.icon_star).
                addItem(R.string.string_from_resource).
                addItem("Item not clickable").notClickable().
                create();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
