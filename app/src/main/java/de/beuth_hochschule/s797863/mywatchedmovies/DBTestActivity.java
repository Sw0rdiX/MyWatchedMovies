package de.beuth_hochschule.s797863.mywatchedmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing all apis
 */
public class DBTestActivity extends AppCompatActivity {
    TextView textView;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        textView = (TextView) findViewById(R.id.result);



    }

    public void saveFilm(View v){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        Film film = new Film().getDummyFilm();
        dbHandler.addMovie(film);
        Log.d("_LOG", "film saved to list !!!");
    }

    public void removeFilm(int id){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        dbHandler.removeMovie(id);
    }

    public void updateFilm(int id){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

    }
    public List<Film> getListFilm(){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        return dbHandler.getListFilms();
    }

    // TODO app abgestört!!!
    public void clearTableContent(){
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        dbHandler.clearTableContent();
        Log.d("_LOG", "table deleted and recreated");
    }
}
