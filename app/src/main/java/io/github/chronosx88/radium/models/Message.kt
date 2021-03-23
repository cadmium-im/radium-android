package io.github.chronosx88.radium.models

data class Message(
        val text: String,
        val author: Boolean,
        val timestamp: Long,
        val read: Boolean
)