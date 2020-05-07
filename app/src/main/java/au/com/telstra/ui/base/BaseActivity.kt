package au.com.telstra.ui.base

import androidx.appcompat.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * A base Actvity that handles injection.
 */
open class BaseActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): DispatchingAndroidInjector<Any> = dispatchingAndroidInjector
}
