package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import android.content.Context
import android.net.wifi.WifiManager
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.core.koin_modules.publicRepositories
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules


@RunWith(AndroidJUnit4::class)
class PublicRepositoriesFragmentTest {

    lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        loadKoinModules(listOf(publicRepositories))
        launchFragmentInContainer<PublicRepositoriesFragment>()
    }

    @Test
    fun shouldDisplayProgressOnFragmentStart() {
        Espresso.onView(ViewMatchers.withId(R.id.loadingProgress))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldNotDisplayErrorOnFragmentStart() {
        Espresso.onView(ViewMatchers.withId(R.id.errorTitle))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun shouldDisplayRecyclerViewWhenContentIsLoaded() {
        Espresso.onView(isRoot()).perform(waitFor(5000L))
        Espresso.onView(ViewMatchers.withId(R.id.repositoriesRV))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorWhenNoConnection() {

        // Imaginando que a conexão esteja desligada
        Espresso.onView(ViewMatchers.withId(R.id.errorTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayProgressOnRepositorySearch() {

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(replaceText("octocat/hello-world"))

        Espresso.onView(ViewMatchers.withId(R.id.loadingProgress))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorOnInvalidRepositorySearch() {

        Espresso.onView(isRoot()).perform(waitFor(5000L))

        Espresso.onView(ViewMatchers.withId(R.id.searchEditText))
            .perform(replaceText("invalid"))

        Espresso.onView(isRoot()).perform(waitFor(2000L))

        Espresso.onView(ViewMatchers.withId(R.id.errorTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}

fun waitFor(delay: Long): ViewAction {
    return object : ViewAction {
        override fun perform(uiController: UiController?, view: View?) {
            uiController?.loopMainThreadForAtLeast(delay)
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isRoot()
        }

        override fun getDescription(): String {
            return "wait for " + delay + "milliseconds"
        }
    }
}