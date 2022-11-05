package com.example.challenge_chapter6_fix.utils

import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class RegisterUtilsTest {

    private val expectedValue = RegisterUtils.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
    lateinit var register : RegisterUtils

    @Before
    fun setUp() {
        register = RegisterUtils
    }

    @After
    fun tearDown() {
    }

    @Test
    fun username_validate(){
        val emptyResult = register.validate("", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val shortResult = register.validate("a", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val longResult = register.validate("hbfewnkmpwfeifnwoeinfwenofwniofwifwijnfweifweiio", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(emptyResult, expectedValue)
        assertEquals(shortResult, expectedValue)
        assertEquals(longResult, expectedValue)
    }

    @Test
    fun name_validate(){
        val emptyResult = register.validate("username", "", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val shortResult = register.validate("username", "a", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(emptyResult, expectedValue)
        assertEquals(shortResult, expectedValue)
    }

    @Test
    fun email_validate(){
        val emptyResult = register.validate("username", "aiffah kiysa", "", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val notContainsResult = register.validate("username", "aiffah kiysa", "aaaab", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(emptyResult, expectedValue)
        assertEquals(notContainsResult, expectedValue)
    }

    @Test
    fun birthday_validate(){
        val emptyResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val notContainsResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "abc", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(emptyResult, expectedValue)
        assertEquals(notContainsResult, expectedValue)
    }

    @Test
    fun nomor_validate(){
        val emptyResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "", "hjbfjewnijw", "hjbfjewnijw")
        val longResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "08213249877067890", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(emptyResult, expectedValue)
        assertEquals(longResult, expectedValue)
    }

    @Test
    fun existingUser(){
        val actualResult = RegisterUtils.validate("abcd", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertEquals(expectedValue, actualResult)
    }

    @Test
    fun passwordToSort(){
        val actualResult = RegisterUtils.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "a", "a")
        assertEquals(expectedValue, actualResult)
    }
    @Test
    fun passwordToLong(){
        val actualResult = RegisterUtils.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfhbwejnofewiffjewerjewjnnijw", "hjbfhbwejnofewiffjewerjewjnnijw")
        assertEquals(expectedValue, actualResult)
    }


}