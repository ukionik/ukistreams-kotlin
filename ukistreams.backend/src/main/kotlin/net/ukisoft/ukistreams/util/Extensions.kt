package net.ukisoft.ukistreams.util

fun <T> T?.default(default: T): T {
    return this ?: default
}
