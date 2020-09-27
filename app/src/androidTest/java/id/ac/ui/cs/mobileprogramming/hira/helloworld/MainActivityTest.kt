package id.ac.ui.cs.mobileprogramming.hira.helloworld

import androidx.test.core.app.ActivityScenario
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    private lateinit var stringinSnackBar: String
    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringinSnackBar = "1"
    }

    @Test fun testPressFab() {
        val scenario: ActivityScenario<MainActivity> = activityRule.scenario
        onView(withId(R.id.fab)).perform(click())
        onView(withText(stringinSnackBar))
            .check(matches(withEffectiveVisibility(
                Visibility.VISIBLE
            )));

    }
}