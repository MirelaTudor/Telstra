package au.com.telstra.di.components

import android.app.Application
import au.com.telstra.TelstraApp
import au.com.telstra.di.modules.ActivityModule
import au.com.telstra.di.modules.ApiModule
import au.com.telstra.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ApiModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: TelstraApp)
}
