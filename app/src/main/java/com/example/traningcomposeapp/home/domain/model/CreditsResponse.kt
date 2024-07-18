package com.example.traningcomposeapp.home.domain.model

data class CreditsResponse(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val castId: Int,
    val commonCastCrew: CommonCastCrew
)

data class Crew(
    val commonCastCrew: CommonCastCrew,
    val department: String,
    val job: String
)

data class CommonCastCrew(
    val adult: Boolean,
    val creditId: String,
    val gender: Int,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String
)