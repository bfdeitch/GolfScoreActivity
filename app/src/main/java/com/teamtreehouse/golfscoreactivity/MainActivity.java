package com.teamtreehouse.golfscoreactivity;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ListActivity {
    private static final String PREFS_FILE = "com.teamtreehouse.golfscoreactivity.preferences";
    private static final String KEY_STROKES = "strokes";
    private SharedPreferences mSharedPreferences;
    private ListAdapter mListAdapter;
    private Hole[] mHoles = new Hole[18];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);

        // Initialize holes
        for (int i = 0; i < mHoles.length; i++) {
            int strokes = mSharedPreferences.getInt(KEY_STROKES + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        mListAdapter = new ListAdapter(this, mHoles);
        setListAdapter(mListAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save holes
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for (int i = 0; i < mHoles.length; i++) {
            editor.putInt(KEY_STROKES + i, mHoles[i].getStrokeCount());
        }
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear_scores) {
            mSharedPreferences.edit().clear().apply();
            for (Hole mHole : mHoles) {
                mHole.setStrokeCount(0);
            }
            mListAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
