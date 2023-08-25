package com.julioolivares90.zeromangaview.utilidades

fun getTextFormated(value : String) : String {

    return if (value.length > 20)
        "${value.substring(0,13)}..."
    else
        value
}