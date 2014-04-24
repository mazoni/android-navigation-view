package mazoni.menulayout.example;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

            }

            @Override
            public void onLongClick(mazoni.menulayout.MenuItem menuItem) {

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
