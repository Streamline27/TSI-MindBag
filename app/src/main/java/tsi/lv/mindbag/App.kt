package tsi.lv.mindbag

import android.app.Application
import tsi.lv.mindbag.di.AppComponent
import tsi.lv.mindbag.di.AppModule
import tsi.lv.mindbag.di.DaggerAppComponent


class App : Application(){

    companion object {
        @JvmStatic lateinit var injector: AppComponent;
    }

    override fun onCreate() {
        super.onCreate()

        injector = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build();
    }

}