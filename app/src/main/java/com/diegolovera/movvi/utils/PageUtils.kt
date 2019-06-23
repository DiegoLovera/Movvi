package com.diegolovera.movvi.utils

object PageUtils {
    var popularPage: Int = 0
    var topRatedPage: Int = 0
    var upcomingPage: Int = 0

    fun resetPages() {
        popularPage = 1
        topRatedPage = 1
        upcomingPage = 1
    }
}