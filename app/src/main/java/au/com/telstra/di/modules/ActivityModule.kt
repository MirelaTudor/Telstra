package au.com.telstra.di.modules

import au.com.telstra.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Each activity that extends BaseActivity (and therefore is injectable) must be defined here.
 * Either set the FragmentModule as submodule or specify your own when a specific Dagger
 * subcomponent is needed.
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    internal abstract fun contributesMainActivity(): MainActivity
}
