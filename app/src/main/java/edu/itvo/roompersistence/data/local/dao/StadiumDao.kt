package edu.itvo.roompersistence.data.local.dao

import androidx.room.*
import edu.itvo.roompersistence.data.local.entity.StadiumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StadiumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStadium(stadium: StadiumEntity)

    @Query("SELECT * FROM stadiums ORDER BY name ASC")
    fun getStadiums(): Flow<List<StadiumEntity>>

    @Delete
    suspend fun deleteStadium(stadium: StadiumEntity)
}