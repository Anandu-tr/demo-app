package com.example.demo_app.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.demo_app.data.repository.FakeRepository
import com.example.demo_app.data.repository.Repository
import com.example.demo_app.di.AppModule
import com.example.demo_app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.After
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var scenario: ActivityScenario<MainActivity>

    @After
    fun clean() {
        scenario.close()
    }

    @Test
    fun successResponse_DisplayList() {
        hiltRule.inject()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.group_success)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.group_error)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText("Test"))))
        onView(withId(R.id.rv_data)).perform(
            RecyclerViewActions.scrollTo<DemoListAdapter.DemoViewHolder>(
                hasDescendant(withText("Test 2"))
            )
        )
    }

    @Test
    fun errorResponse_showErrorMessage() {
        TestAppModule.returnError = true
        hiltRule.inject()
        scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.group_success)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.group_error)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        scenario.close()
    }
}

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [AppModule::class])
object TestAppModule {

    var returnError = false

    @Provides
    fun provideDemoRepository(): Repository {
        return FakeRepository().apply {
            setReturnError(returnError)
        }
    }
}