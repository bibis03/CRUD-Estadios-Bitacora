package edu.itvo.roompersistence.data.mapper

import edu.itvo.roompersistence.data.local.entity.StadiumEntity
import edu.itvo.roompersistence.domain.model.Stadium

fun StadiumEntity.toDomain() = Stadium(
    id = id,
    name = name,
    city = city,
    capacity = capacity,
    country = country
)

fun Stadium.toEntity() = StadiumEntity(
    id = id,
    name = name,
    city = city,
    capacity = capacity,
    country = country
)