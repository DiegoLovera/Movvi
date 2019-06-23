package com.diegolovera.movvi.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

@Entity(tableName = "movie_details")
public class MovieDetails {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private long id;

    @SerializedName("adult")
    @Expose
    @ColumnInfo(name = "adult")
    private boolean adult;

    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = "backdropPath")
    private String backdropPath;

    @SerializedName("budget")
    @Expose
    @ColumnInfo(name = "budget")
    private float budget;

    @SerializedName("genres")
    @Expose
    @Ignore
    private List<Genre> genres;

    @SerializedName("homepage")
    @Expose
    @ColumnInfo(name = "homepage")
    private String homepage;

    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = "originalLanguage")
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = "originalTitle")
    private String originalTitle;

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = "popularity")
    private float popularity;

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @SerializedName("production_countries")
    @Expose
    @Ignore
    private List<ProductionCountry> productionCountries;

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "releaseDate")
    private Date releaseDate;

    @SerializedName("revenue")
    @Expose
    @ColumnInfo(name = "revenue")
    private float revenue;

    @SerializedName("runtime")
    @Expose
    @ColumnInfo(name = "runtime")
    private int runtime;

    @SerializedName("status")
    @Expose
    @ColumnInfo(name = "status")
    private String status;

    @SerializedName("tagline")
    @Expose
    @ColumnInfo(name = "tagline")
    private String tagline;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("video")
    @Expose
    @ColumnInfo(name = "video")
    private boolean video;

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "voteAverage")
    private float voteAverage;

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "voteCount")
    private int voteCount;

    public MovieDetails() {
    }

    public MovieDetails(long id, boolean adult, String backdropPath, float budget, List<Genre> genres, String homepage, String originalLanguage, String originalTitle, String overview, float popularity, String posterPath, List<ProductionCountry> productionCountries, Date releaseDate, float revenue, int runtime, String status, String tagline, String title, boolean video, float voteAverage, int voteCount) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
