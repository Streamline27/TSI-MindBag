package tsi.lv.mindbag.di

import dagger.Component
import tsi.lv.mindbag.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}