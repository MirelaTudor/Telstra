package au.com.telstra

import android.app.Application
import au.com.telstra.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

class TelstraApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("telstra.realm").build()
        Realm.setDefaultConfiguration(config)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector
}
