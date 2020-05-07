package au.com.telstra.data.domain

import au.com.telstra.data.domain.model.FactEntityList
import kotlinx.coroutines.flow.Flow

interface FactRepository {

    suspend fun refreshFacts(): Flow<FactEntityList>
}
