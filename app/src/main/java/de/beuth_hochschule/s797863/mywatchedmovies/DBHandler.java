package de.beuth_hochschule.s797863.mywatchedmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tungtongduc on 19.01.16.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AndroidDevelopment";
    private static final String TABLE_MOVIES = "Movies";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_REALEASE = "REALEASE";
    public static final String COLUMN_RUNTIME = "RUNTIME";
    public static final String COLUMN_GENRE = "GENRE";
    public static final String COLUMN_DIRECTOR = "DIRECTOR";
    public static final String COLUMN_WRITER = "WRITER";
    public static final String COLUMN_ACTORS = "ACTORS";
    public static final String COLUMN_LANGUAGE = "LANGUAGE";
    public static final String COLUMN_COUNTRY = "COUNTRY";
    public static final String COLUMN_AWARD = "AWARD";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_RATING = "imdbRating";
    public static final String COLUMN_ISWATCHED = "isWatched";


    /**
     * Constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * create table MOVIE
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE =
                "CREATE TABLE " + TABLE_MOVIES +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_REALEASE + " TEXT," +
                COLUMN_RUNTIME + " TEXT," +
                COLUMN_GENRE + " TEXT," +
                COLUMN_DIRECTOR + " TEXT," +
                COLUMN_WRITER + " TEXT," +
                COLUMN_ACTORS + " TEXT," +
                COLUMN_LANGUAGE + " TEXT," +
                COLUMN_COUNTRY + " TEXT," +
                COLUMN_AWARD + " TEXT," +
                COLUMN_TYPE + " TEXT," +
                COLUMN_RATING + " TEXT," +
                COLUMN_ISWATCHED + " INTEGER" +
                ")";
        Log.d("_LOG", CREATE_MOVIES_TABLE);
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    /**
     *  on upgrade table, may be use method updateMove instead!
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    /**
     * for adding a new film into database
     * @param film
     */
    public void addMovie(Film film){
        ContentValues values = new ContentValues();
        film.setIsWatched(1);

        values.put(COLUMN_ID, film.getId());
        values.put(COLUMN_TITLE, film.getTitle());
        values.put(COLUMN_REALEASE, film.getReleased());
        values.put(COLUMN_RUNTIME, film.getRuntime());
        values.put(COLUMN_GENRE, film.getGenre());
        values.put(COLUMN_DIRECTOR, film.getDirector());
        values.put(COLUMN_WRITER, film.getWriter());
        values.put(COLUMN_ACTORS, film.getActors());
        values.put(COLUMN_LANGUAGE, film.getLanguage());
        values.put(COLUMN_COUNTRY, film.getCountry());
        values.put(COLUMN_AWARD, film.getAward());
        values.put(COLUMN_TYPE, film.getType());
        values.put(COLUMN_RATING, film.getImdbRating());
        values.put(COLUMN_ISWATCHED, film.isWatched());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    /**
     * remove a film from database
     * @param movieId
     * @return
     */
    public boolean removeMovie(int movieId){
        boolean removed = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT FROM " + TABLE_MOVIES +
                " WHERE " + COLUMN_ID + " = " + movieId;
        Cursor cursor = db.rawQuery(query, null);

        Film film = new Film();
        if(cursor.moveToFirst()){
            film.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_MOVIES, COLUMN_ID + " = ?", new String[] {String.valueOf(film.getId())});
            cursor.close();
            removed = true;
        }
        db.close();
        return removed;
    }

    /**
     * update Film from database
     * @param movieId
     * @return
     */
    //TODO
    public boolean updateMovie(int movieId){
        boolean updated = false;

        return updated;
    }

    /**
     * find a existed Film in database
     * @param filmId
     * @return new object film if films id exists in the database
     */
    public Film findMovieById(int filmId){
        Film f = null;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES +
                " WHERE " + COLUMN_ID + " = " + filmId;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() != 0){
            f = new Film();
            f.setId(cursor.getInt(0));
            f.setTitle(cursor.getString(1));
            f.setReleased(cursor.getString(2));
            f.setRuntime(cursor.getString(3));
            f.setGenre(cursor.getString(4));
            f.setDirector(cursor.getString(5));
            f.setWriter(cursor.getString(6));
            f.setActors(cursor.getString(7));
            f.setLanguage(cursor.getString(8));
            f.setCountry(cursor.getString(9));
            f.setAward(cursor.getString(10));
            f.setType(cursor.getString(11));
            f.setImdbRating(cursor.getString(12));
            f.setIsWatched(cursor.getInt(13));
        }

        db.close();
        return f;
    }

    /**
     *
     * @param filmId
     * @return true if film exists in database
     */
    public boolean isFilmExist(int filmId){
        return findMovieById(filmId) != null ? true : false;
    }

    /**
     * clear the databases content
     */
    public void clearTableContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    /**
     *
     * @return list of films from database
     */
    public List<Film> getListFilms(){
        List<Film> movies = new ArrayList<Film>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++) {
                Film f = new Film();
                f.setId(cursor.getInt(0));
                f.setTitle(cursor.getString(1));
                f.setReleased(cursor.getString(2));
                f.setRuntime(cursor.getString(3));
                f.setGenre(cursor.getString(4));
                f.setDirector(cursor.getString(5));
                f.setWriter(cursor.getString(6));
                f.setActors(cursor.getString(7));
                f.setLanguage(cursor.getString(8));
                f.setCountry(cursor.getString(9));
                f.setAward(cursor.getString(10));
                f.setType(cursor.getString(11));
                f.setImdbRating(cursor.getString(12));
                f.setIsWatched(cursor.getInt(13));

                movies.add(f);
                cursor.moveToNext();
            }
        }

        return movies;
    }
}
