package com.example.challenge_chapter6_fix.utils

import org.junit.Assert

import org.junit.Test

class LoginUtilsTest {

    private val expectedValue = LoginUtils.validate("email@gmail.com", "saifenjfewo")
    @Test
    fun email_is_empty() {
        val actualResult = LoginUtils.validate("", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun email_length_less_than_6() {
        val actualResult = LoginUtils.validate("abcd", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun email_length_very_long(){
        val actualResult = LoginUtils.validate("aaaaaaaaaaaaaaaaaaaa", "saifenjfewo")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun existingEmail(){
        val actualResult = LoginUtils.validate("aaaa@gmail.com", "dkjafjla")
        Assert.assertEquals(expectedValue, actualResult)
    }

    @Test
    fun passwordToSort(){
        val actualResult = LoginUtils.validate("fewnkewm@gmail.com", "a")
        Assert.assertEquals(expectedValue, actualResult)
    }
    @Test
    fun passwordToLong(){
        val actualResult = LoginUtils.validate("fewnkewm@gmail.com", "sjadknainfweaaaaaaaaaaaaaaa")
        Assert.assertEquals(expectedValue, actualResult)
    }
}