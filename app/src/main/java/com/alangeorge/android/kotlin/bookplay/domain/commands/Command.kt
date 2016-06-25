package com.alangeorge.android.kotlin.bookplay.domain.commands

interface Command<T> {
    fun execute(): T
}