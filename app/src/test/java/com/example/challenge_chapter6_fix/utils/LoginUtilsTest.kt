package com.example.challenge_chapter6_fix.utils

import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class LoginUtilsTest {

    private val expectedValue = LoginUtils.validate("email@gmail.com", "saifenjfewo")
    lateinit var login : LoginUtils

    @Before
    fun setUp() {
        login = LoginUtils
    }

    @After
    fun tearDown() {
    }

    @Test
    fun email_is_empty() {
        val actualResult = login.validate("", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun email_length_less_than_6() {
        val actualResult = login.validate("abcd", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun email_length_very_long(){
        val actualResult = login.validate("aaaaaaaaaaaaaaaaaaaa", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun existingEmail(){
        val actualResult = login.validate("aaaa@gmail.com", "dkjafjla")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun passwordToSort(){
        val actualResult = login.validate("fewnkewm@gmail.com", "a")
        Assert.assertEquals(expectedValue, actualResult)
    }
    @Test
    fun passwordToLong(){
        val actualResult = login.validate("fewnkewm@gmail.com", "sjadknainfweaaaaaaaaaaaaaaa")
        Assert.assertEquals(expectedValue, actualResult)
    }
}