package com.almarpa.dragonballapp.core.navigation

import kotlinx.serialization.Serializable

/**
 * Destinations used throughout the app.
 */
sealed class Routes {

    @Serializable
    data object Home

    @Serializable
    data class Detail(val id:Int)
}
