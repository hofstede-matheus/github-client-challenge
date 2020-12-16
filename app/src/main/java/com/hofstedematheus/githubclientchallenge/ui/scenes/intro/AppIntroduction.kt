package com.hofstedematheus.githubclientchallenge.ui.scenes.intro

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.data.constants.IS_FIRST_TIME_OPEN
import com.hofstedematheus.githubclientchallenge.data.constants.PREFERENCES_NAME
import com.hofstedematheus.githubclientchallenge.data.constants.PURPLE_200
import com.hofstedematheus.githubclientchallenge.data.constants.TEAL_200

class AppIntroduction : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        with(preferences.edit()) {
            putBoolean(IS_FIRST_TIME_OPEN, false)
            apply()
        }
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        with(preferences.edit()) {
            putBoolean(IS_FIRST_TIME_OPEN, false)
            apply()
            getPreferences(Context.MODE_PRIVATE).getBoolean(IS_FIRST_TIME_OPEN, true)
        }
        finish()
    }
}