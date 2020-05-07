package au.com.telstra.data.repository

import au.com.telstra.data.domain.FactRepository
import au.com.telstra.data.domain.mapper.FactEntityMapper
import au.com.telstra.data.remote.FactService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A repository class, used to collect the data from networking or from cache
 */
class FactRepositoryImpl @Inject constructor(
    private val factService: FactService,
    private val factEntityMapper: FactEntityMapper
) : FactRepository {

    @Throws(Exception::class)
    override suspend fun refreshFacts() = flow {
        val remoteData = factService.getFacts()
        emit(
            if (remoteData.isSuccessful) {
                val data = remoteData.body()
                if (data != null) {
                    factEntityMapper.mapFromRemote(data)
                } else {
                    throw NullPointerException()
                }
            } else {
                throw IllegalStateException()
            }
        )
    }
}

