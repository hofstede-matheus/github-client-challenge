package com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.core.koin_modules.publicRepositories
import com.hofstedematheus.githubclientchallenge.core.koin_modules.repositoryPage
import com.hofstedematheus.githubclientchallenge.data.constants.REPOSITORY
import com.hofstedematheus.githubclientchallenge.data.model.Owner
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories.PublicRepositoriesFragment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import io.mockk.mockk

@RunWith(AndroidJUnit4::class)
class RepositoryPageFragmentTest {

    @Before
    fun setUp() {
        loadKoinModules(listOf(repositoryPage))

        val publicRepository = PublicRepository(
            owner = Owner()
        )
        val fragmentArgs = bundleOf(REPOSITORY to publicRepository)
        launchFragmentInContainer<RepositoryPageFragment>(fragmentArgs)
    }

    @Test
    fun shouldDisplayAllViewsOnFragmentStart() {
        Espresso.onView(ViewMatchers.withId(R.id.repositoryNameTXT))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.repositoryDescriptionTXT))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.profileImageIMG))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ownerNameLabelTXT))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.ownerNameTXT))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}