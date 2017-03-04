package com.hrupin.sample.dynamicoptionsmenusample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_EDIT = 0;
    private static final int MENU_DELETE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.add(0, MENU_EDIT, Menu.NONE, getString(R.string.menu_action_edit)).setIcon(R.drawable.ic_action_edit).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, MENU_DELETE, Menu.NONE, getString(R.string.menu_action_delete)).setIcon(R.drawable.ic_action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_EDIT:
                Toast.makeText(this, "Edit menu item clicked", Toast.LENGTH_SHORT).show();
                break;
            case MENU_DELETE:
                Toast.makeText(this, "Delete menu item clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
