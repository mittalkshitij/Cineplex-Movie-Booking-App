package com.example.traningcomposeapp.utils

import com.example.traningcomposeapp.launcher.data.model.ImagesConfiguration

object ImageConfig {
    var imageConfig: ImagesConfiguration? = null

    var assetBaseUrl = imageConfig?.let {
        it.images?.secureBaseUrl
    } ?: "https://image.tmdb.org/t/p/"

    var backdropSize = imageConfig?.let {
        it.images?.posterSizes
    } ?: listOf("w300", "w780", "w1280", "original")

    var posterSize = imageConfig?.let {
        it.images?.posterSizes
    } ?: listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")

    var logoSize = imageConfig?.let {
        it.images?.logoSizes
    } ?: listOf("w45", "w92", "w154", "w185", "w300", "w500", "original")

    var profileSize = imageConfig?.let {
        it.images?.profileSizes
    } ?: listOf("w45", "w185", "h632", "original")

    var stillSize = imageConfig?.let {
        it.images?.stillSizes
    } ?: listOf("w92", "w185", "w300", "original")
}