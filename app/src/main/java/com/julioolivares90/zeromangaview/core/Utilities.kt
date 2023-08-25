package com.julioolivares90.zeromangaview.core

fun getImageFromMangaUrl(imagenUrl : String) = imagenUrl
    .substring(74,imagenUrl.length)
    .replace("');","")
    .replace("}","")