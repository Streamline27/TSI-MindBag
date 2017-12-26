package tsi.lv.mindbag.di

import dagger.Component
import tsi.lv.mindbag.screens.notes.MainActivity
import tsi.lv.mindbag.screens.content.ContentActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(contentActivity: ContentActivity)

}