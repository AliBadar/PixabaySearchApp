package com.pixabay.app

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestMainActivity {

    @Rule @JvmField var mainActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = mainActivityTestRule.activity
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLaunch(){
        val view: View = mainActivity.findViewById(R.id.imgRefresh)

        assertNotNull(view)
    }
}