package com.example.traningcomposeapp.home.domain.mapper

import com.example.traningcomposeapp.common.domain.Mapper
import com.example.traningcomposeapp.home.data.model.CastDTO
import com.example.traningcomposeapp.home.data.model.CreditsResponseDTO
import com.example.traningcomposeapp.home.data.model.CrewDTO
import com.example.traningcomposeapp.home.domain.model.Cast
import com.example.traningcomposeapp.home.domain.model.CommonCastCrew
import com.example.traningcomposeapp.home.domain.model.CreditsResponse
import com.example.traningcomposeapp.home.domain.model.Crew
import com.example.traningcomposeapp.utils.Constants.EMPTY

//.filter {
//    it.knownForDepartment == "Acting"
//}?

//.filter {
//    it.knownForDepartment == "Production"
//}?
class CreditsResponseMapper : Mapper<CreditsResponseDTO, CreditsResponse> {
    override fun mapFrom(from: CreditsResponseDTO): CreditsResponse {
        return CreditsResponse(
            id = from.id ?: 0,
            cast = from.cast?.map {
                CastResponseMapper().mapFrom(it)
            } ?: emptyList(),
            crew = from.crew?.map { CrewResponseMapper().mapFrom(it) }
                ?: emptyList()
        )
    }
}

class CastResponseMapper : Mapper<CastDTO, Cast> {
    override fun mapFrom(from: CastDTO): Cast {
        return Cast(
            castId = from.castId ?: 0,
            commonCastCrew = CommonCastCrew(
                adult = from.adult ?: true,
                creditId = from.creditId ?: EMPTY,
                gender = from.gender ?: 0,
                id = from.id ?: 0,
                knownForDepartment = from.knownForDepartment ?: EMPTY,
                name = from.name ?: EMPTY,
                originalName = from.originalName ?: EMPTY,
                popularity = from.popularity ?: 0.0,
                profilePath = from.profilePath ?: EMPTY
            )
        )
    }
}

class CrewResponseMapper : Mapper<CrewDTO, Crew> {
    override fun mapFrom(from: CrewDTO): Crew {
        return Crew(
            department = from.department ?: EMPTY,
            job = from.job ?: EMPTY,
            commonCastCrew = CommonCastCrew(
                adult = from.adult ?: true,
                creditId = from.creditId ?: EMPTY,
                gender = from.gender ?: 0,
                id = from.id ?: 0,
                knownForDepartment = from.knownForDepartment ?: EMPTY,
                name = from.name ?: EMPTY,
                originalName = from.originalName ?: EMPTY,
                popularity = from.popularity ?: 0.0,
                profilePath = from.profilePath ?: EMPTY
            )
        )
    }
}