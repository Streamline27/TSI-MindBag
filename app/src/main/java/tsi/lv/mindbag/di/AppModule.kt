package tsi.lv.mindbag.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext() : Application = application;

    @Provides
    @Singleton
    fun provideHello() : HelloDagger = HelloDagger();

}