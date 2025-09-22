package com.example.getitdone.navigation

data class NavData(
    var route: String = "",
    var isInclusive: Boolean = false,
    var isSaveState: Boolean = false
)