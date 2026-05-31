package edu.itvo.roompersistence.data.repository

import edu.itvo.roompersistence.data.local.dao.StadiumDao
import edu.itvo.roompersistence.data.mapper.toDomain
import edu.itvo.roompersistence.data.mapper.toEntity
import edu.itvo.roompersistence.domain.model.Stadium
import edu.itvo.roompersistence.domain.repository.StadiumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StadiumRepositoryImpl @Inject constructor(
    private val dao: StadiumDao
) : StadiumRepository {

    override suspend fun insertStadium(stadium: Stadium) {
        dao.insertStadium(stadium.toEntity())
    }

    override fun getStadiums(): Flow<List<Stadium>> {
        return dao.getStadiums().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun deleteStadium(stadium: Stadium) {
        dao.deleteStadium(stadium.toEntity())
    }
}