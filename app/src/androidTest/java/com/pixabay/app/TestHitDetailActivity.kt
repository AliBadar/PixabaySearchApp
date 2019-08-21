package com.pixabay.app

import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.view.View
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestHitDetailActivity {

    @Rule @JvmField var hitDetailActivityTestRule = ActivityTestRule<HitDetailActivity>(HitDetailActivity::class.java)

    private lateinit var hitDetailActivity: HitDetailActivity

    @Before
    fun setUp() {
        hitDetailActivity = hitDetailActivityTestRule.activity
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testLaunch(){
        val view: View = hitDetailActivity.findViewById(R.id.ivLarge)

        assertNotNull(view)
    }
}