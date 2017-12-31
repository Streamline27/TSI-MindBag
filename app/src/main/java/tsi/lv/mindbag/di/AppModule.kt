package tsi.lv.mindbag.di

import android.app.Application
import dagger.Module
import dagger.Provides
import tsi.lv.mindbag.model.DatabaseModel
import tsi.lv.mindbag.model.MockModel
import tsi.lv.mindbag.model.Model
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
    fun provideModel(ctx : Application) : Model = DatabaseModel(ctx)
}