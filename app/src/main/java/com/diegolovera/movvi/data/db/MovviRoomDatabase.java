package com.diegolovera.movvi.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.diegolovera.movvi.data.db.daos.*;
import com.diegolovera.movvi.data.models.*;

@Database(entities = {
        Movie.class,
        MovieDetails.class,
        Genre.class,
        ProductionCountry.class,
        UniqueGenre.class
},
        version = 1,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MovviRoomDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract MovieDetailsDao movieDetailsDao();
    public abstract GenreDao genreDao();
    public abstract ProductionCountryDao productionCountryDao();
    public abstract UniqueGenreDao uniqueGenreDao();

    private static volatile MovviRoomDatabase mInstance;

    public static MovviRoomDatabase getInstance(final Context context) {
        if (mInstance == null) {
            synchronized (MovviRoomDatabase.class) {
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovviRoomDatabase.class, "movvi_database")
                        .build();
            }
        }
        return mInstance;
    }
}
