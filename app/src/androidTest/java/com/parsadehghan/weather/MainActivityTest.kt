package com.parsadehghan.weather


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class MainActivityTest {



    @Test
    fun mainActivityTest() {
        ActivityScenario.launch(MainActivity::class.java)
        val recyclerView = onView(
            allOf(
                withId(R.id.rvDays),
                childAtPosition(
                    withId(R.id.container),
                    10
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(4, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(6, click()))

        val textView = onView(
            allOf(
                withId(R.id.txtDay), withText("Saturday"),
                withParent(withParent(withId(R.id.rvDays))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Saturday")))

        textView.check(matches(withText("Friday")))

        textView.check(matches(withText("Thursday")))

        textView.check(matches(withText("Monday")))

        textView.check(matches(withText("Monday")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
