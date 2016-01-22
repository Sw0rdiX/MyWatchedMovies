package de.beuth_hochschule.s797863.mywatchedmovies;

/**
 * Created by tungtongduc on 19.01.16.
 */
public class Film {
    int id;
    String title;
    String released;
    String runtime;
    String genre;
    String director;
    String writer;
    String actors;
    String language;
    String country;
    String award;
    String type;
    String imdbRating;
    // 0 == false/ 1 == true
    int isWatched;

    public Film (){}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAward() {
        return award;
    }

    public String getType() {
        return type;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public int isWatched() {
        return isWatched;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setIsWatched(int isWatched) {
        this.isWatched = isWatched;
    }


    /**
     *
     * @return a dummy Film for testing
     */
    public Film getDummyFilm(){

        this.setTitle("One Day");
        this.setIsWatched(1);
        this.setType("series");
        this.setImdbRating("12");
        this.setActors("Donald Duck");
        this.setAward("MTV Music Award");
        this.setCountry("USA");
        this.setDirector("Michael Bay");
        this.setGenre("Action");
        this.setLanguage("English");
        this.setReleased("17 May 1978");
        this.setRuntime("90 min");
        this.setWriter("Victor Hugo");

        return this;
    }

}
