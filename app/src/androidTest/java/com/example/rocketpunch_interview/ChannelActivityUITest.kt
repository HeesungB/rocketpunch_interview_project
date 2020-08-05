package com.example.rocketpunch_interview

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.rocketpunch_interview.ui.channel.ChannelActivity
import com.example.rocketpunch_interview.ui.login.LoginActivity
import com.example.rocketpunch_interview.ui.new_channel.NewChannelActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChannelActivityUITest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(ChannelActivity::class.java)

    /**
     *  @Given  뷰가 초기화 된 후
     *  @When   뷰가 그려졌을 때
     *  @Then   뷰의 속성 확인
     */
    @Test
    fun checkChannelViewContentWhenViewLoadedWithoutLogin() {
        onView(ViewMatchers.withId(R.id.logout_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(ViewMatchers.withId(R.id.title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(ViewMatchers.withId(R.id.new_channel_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     *  @Given  로그인 된 후
     *  @When   새 메시지 버튼 클릭했을 때
     *  @Then   NewChannelActivity로 이동하는 지 확인
     */
    @Test
    fun isMoveToNewChannelActivityWhenOnClickLoginButton() {
        Intents.init()
        onView(ViewMatchers.withId(R.id.new_channel_button)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(NewChannelActivity::class.java.name))
        Intents.release()
    }

    /**
     *  @Given  로그인 된 후
     *  @When   로그아웃 버튼 클릭했을 때
     *  @Then   LoginActivity로 이동하는 지 확인
     */
    @Test
    fun isMoveToLoginChannelActivityWhenOnClickLogoutButton() {
        Intents.init()
        onView(ViewMatchers.withId(R.id.logout_button)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(LoginActivity::class.java.name))
        Intents.release()

        onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click())
    }

}