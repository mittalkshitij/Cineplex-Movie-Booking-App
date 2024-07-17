package com.example.traningcomposeapp.common.domain

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}