package com.hofstedematheus.githubclientchallenge.ui.scenes.intro

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.data.constants.PURPLE_200
import com.hofstedematheus.githubclientchallenge.data.constants.TEAL_200

class AppIntroduction : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.intro1_title),
                description = getString(R.string.intro1_description),
                backgroundColor = Color.parseColor(TEAL_200),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,

            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = getString(R.string.intro2_title),
                description = getString(R.string.intro2_description),
                backgroundColor = Color.parseColor(PURPLE_200),
                titleColor = Color.WHITE,
                descriptionColor = Color.WHITE,
            )
        )


    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        finish()
    }
}