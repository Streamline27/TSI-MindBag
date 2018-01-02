package tsi.lv.mindbag.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import tsi.lv.mindbag.model.DatabaseModel
import tsi.lv.mindbag.model.MockModel
import tsi.lv.mindbag.model.Model
import tsi.lv.mindbag.model.database.AppDatabase
import tsi.lv.mindbag.model.database.BookDao
import tsi.lv.mindbag.model.database.NoteDao
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext() : Application = application


    @Provides
    @Singleton
    fun provideHello() : HelloDagger = HelloDagger()

    @Provides
    @Singleton
    fun provideSharedPreferences(ctx: Application) = ctx.getSharedPreferences("MINDBAG_PREF", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideRoomDatabase(ctx: Application) = Room.databaseBuilder(ctx, AppDatabase::class.java, "db-name")
            .allowMainThreadQueries()
            .build();

    @Provides
    @Singleton
    fun provideNoteDao(db : AppDatabase) = db.noteDao()

    @Provides
    @Singleton
    fun provideBookDao(db : AppDatabase) = db.bookDao()

    @Provides
    @Singleton
    fun provideModel(preferences: SharedPreferences,
                     noteDao: NoteDao,
                     bookDao: BookDao) : Model =
            DatabaseModel(preferences, noteDao, bookDao)
}