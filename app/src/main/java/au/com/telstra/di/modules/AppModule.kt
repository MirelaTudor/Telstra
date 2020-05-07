package au.com.telstra.di.modules

import android.app.Application
import android.content.Context
import au.com.telstra.data.domain.FactRepository
import au.com.telstra.data.repository.FactRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * App dependencies that are specific to the app should be defined here.
 */
@Module(includes = [ViewModelModule::class])
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindFactRepository(factRepository: FactRepositoryImpl): FactRepository
}
