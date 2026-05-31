package edu.itvo.roompersistence.domain.model

data class Stadium(
    val id: Long = 0,
    val name: String,
    val city: String,
    val capacity: Int,
    val country: String
)