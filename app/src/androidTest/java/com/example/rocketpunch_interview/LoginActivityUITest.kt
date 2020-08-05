package com.example.rocketpunch_interview


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.rocketpunch_interview.ui.channel.ChannelActivity
import com.example.rocketpunch_interview.ui.login.LoginActivity
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityUITest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    /**
     *  @Given  뷰가 초기화 된 후
     *  @When   뷰가 그려졌을 때
     *  @Then   뷰의 속성 확인
     */
    @Test
    fun checkLoginViewContentWhenViewLoadedWithoutLogin() {
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
    }

    /**
     *  @Given  뷰가 초기화 된 후
     *  @When   Login Button 클릭했을 때
     *  @Then   ChannelActivity로 이동하는 지 확인
     */
    @Test
    fun isMoveToChannelActivityWhenOnClickLoginButton() {
        Intents.init()
        onView(withId(R.id.login_button)).perform(click())
        intended(hasComponent(ChannelActivity::class.java.name))
        Intents.release()
    }
}