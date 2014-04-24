package mazoni.menulayout.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import mazoni.menulayout.MenuLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MenuLayout menuLayout = (MenuLayout) findViewById(R.id.menu_layout);
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
        mazoni.menulayout.MenuItem.Listener specialListener = new mazoni.menulayout.MenuItem.Listener() {
            @Override
            public void onClick(mazoni.menulayout.MenuItem menuItem) {
                Log.d("SpecialItemClicked", String.format("menuItem clicked %s",menuItem.getTag()));
            }

            @Override
            public void onLongClick(mazoni.menulayout.MenuItem menuItem) {
                Log.d("SpecialItemLongClicked", String.format("menuItem long clicked %s",menuItem.getTag()));
            }
        };
        menuLayoutBuilder.inform(listener).
                addItem("Category 1").asSection().
                addItem("Item 1").
                addItem("item with icon").withIcon(R.drawable.icon_star).tagged("with-icon").
                addItem("Special Item").inform(specialListener).
                addItem("Category 2").asSection().
                addItem("item with icon").withIcon(R.drawable.icon_star).
                addItem("Item 1").
                addItem("Item 2").
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
