package com.calhacksproject.teamfunkybeefwrapthings.movietrak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Main extends FragmentActivity {

    static public ArrayList<Movie> movieItems = new ArrayList<Movie>();
    private ArrayAdapter movieItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.mainfragmentholder);
        //initializeLists();

        Intent newIntent = getIntent();


        Movie mainMovie = (Movie) newIntent.getSerializableExtra("movie");
        if(mainMovie != null) {
            movieItems.add(mainMovie);
        }


        Fragment fragment = new MainFragment();

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainFragmentHolder, fragment);
        transaction.commit();

    }
/**
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
       String movieData = intent.getStringExtra("data");
        String title = movieData.split("%%")[0];
       String theaterDate = movieData.split("%%")[1];
        movieItems.add(movieData);
        System.out.println(movieData);

    }
*/
    int flag = 0;

    public void fragmentSwitcher(){
        Fragment fragment = new SearchFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainFragmentHolder, fragment);
        transaction.commit();
        flag = 1;

    }

    @Override
    public void onBackPressed() {
        if(flag == 1){
            Fragment fragment = new MainFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainFragmentHolder, fragment);
            transaction.commit();
            flag = 0;
        }
    }

//    private void initializeLists(){
//        //ScrollView scrollinglistContainer;
//        //scrollinglistContainer = (ScrollView) findViewById((Integer)R.id.scrollingList);
//
//        //json request code
//        //private void refreshMoviesList(String[] movieTitles)
//        // {
//        //    moviesList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, movieTitles));
//        //}
//
//        //Rotten tomatoes API CODE GOES HERE
//        final String API_KEY = "w78ab8sd5wzx3c2zuh3wakcu";
//
//        //ListView listView = (ListView) findViewById(R.id.listView);
//
//        new RequestTask(listView, this).execute("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=" +
//                API_KEY + "&q=" + "Batman" + "&page_limit=" + 15);
//
//
//        //End of API CODE
//
//
//
//        ListView savedMovieList = new ListView(this);
//
//
//        //String[] stringArray = new String[] { "Bright Mode", "Normal Mode" };
//        String testString = "EMPTY";
//
//        //ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
//        //savedMovieList.setAdapter(modeAdapter);
//
//
//        // Adds saved movie list to container...
//        //scrollinglistContainer.addView(savedMovieList);
//    }

    /*
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
    */
}
