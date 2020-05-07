package au.com.telstra.fact

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import au.com.telstra.FactListFragmentFactory
import au.com.telstra.R
import au.com.telstra.ui.fact.FactListFragment
import au.com.telstra.ui.fact.FactView
import au.com.telstra.util.RecyclerViewMatcher
import au.com.telstra.util.ToastMatcher
import io.mockk.every
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FactListFragmentTest {

    private lateinit var fragmentFactory: FactListFragmentFactory

    @Before
    fun setUp() {
        fragmentFactory = FactListFragmentFactory()
    }

    @Test
    fun testDataIsDisplayed() {
        // given
        val facListLiveData = MutableLiveData<List<FactView.DataModel>>().apply {
            postValue(
                listOf(
                    FactView.DataModel("Item 1", "Description 1", null),
                    FactView.DataModel("Item 2", "Description 2", null)
                )
            )
        }

        val titleLiveData = MutableLiveData<String>().apply { postValue("title") }
        val errorMessageResLiveData = MutableLiveData<Int>()

        val viewModel = fragmentFactory.viewModel
        every { viewModel.facListLiveData } returns facListLiveData
        every { viewModel.titleLiveData } returns titleLiveData
        every { viewModel.errorMessageResLiveData } returns errorMessageResLiveData

        // when
        launchFragmentInContainer<FactListFragment>(factory = fragmentFactory)

        // then
        onView(
            allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar)))
        ).check(matches(withText("title")))

        onView(factListMatcher().atPosition(0))
            .check(matches(hasDescendant(withText("Item 1"))))
        onView(factListMatcher().atPosition(1))
            .check(matches(hasDescendant(withText("Description 2"))))
    }

    @Test
    fun testErrorToastIsDisplayed() {
        // given
        val errorMessageResLiveData = MutableLiveData<Int>(R.string.default_error_message)

        val viewModel = fragmentFactory.viewModel
        every { viewModel.errorMessageResLiveData } returns errorMessageResLiveData

        // when
        launchFragmentInContainer<FactListFragment>(factory = fragmentFactory)

        // then
        onView(withText(R.string.default_error_message)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    fun isToast() = ToastMatcher()

    private fun factListMatcher() =  RecyclerViewMatcher(R.id.fact_list)
}
