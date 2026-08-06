package com.example.myapplication // Make sure this matches your package name at the top!

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Greeting(val userName: String)