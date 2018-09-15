package com.example.luthfihariz.footballclub

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.example.luthfihariz.footballclub.ui.matches.MatchesViewHolder
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MatchesActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MatchesActivity::class.java)


    @Test
    fun testAddToFavorite() {
        onView(withId(R.id.bnvMainNav)).check(matches(isDisplayed()))
        onView(withId(R.id.pbMatches)).check(matches(isDisplayed()))

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rvMatches)).apply {
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.scrollToPosition<MatchesViewHolder>(10))
            perform(RecyclerViewActions.actionOnItemAtPosition<MatchesViewHolder>(10, click()))
        }

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.add_to_favorite)).apply {
            check(matches(isDisplayed()))
            perform(click())
        }


        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }


        onView(withText(activityRule.activity.getString(R.string.added_to_fav)))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.bnvMainNav)).check(matches(isDisplayed()))
        onView(withId(R.id.action_fav_match)).perform(click())
        onView(withId(R.id.rvMatches)).check(matches(isDisplayed()))

    }
}