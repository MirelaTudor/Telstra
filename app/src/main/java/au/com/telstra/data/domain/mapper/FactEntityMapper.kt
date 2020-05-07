package au.com.telstra.data.domain.mapper

import au.com.telstra.data.database.FactsDao
import au.com.telstra.data.domain.model.FactEntity
import au.com.telstra.data.domain.model.FactEntityList
import au.com.telstra.data.remote.FactListResponse
import javax.inject.Inject

/**
 * Mapper used to map the data from network to domain data,
 * also filter the rows that has all the content null
 */
class FactEntityMapper @Inject constructor() {

    fun mapFromRemote(factResponse: FactListResponse) = FactEntityList(
        factResponse.title,
        factResponse.rows.filter {
            it.title != null || it.description != null || it.imageHref != null
        }.map {
            FactEntity(it.title, it.description, it.imageHref)
        }
    )

    fun mapFromLocal(factsDao: FactsDao) = FactEntityList(
        factsDao.title,
        factsDao.list.map {
            FactEntity(it.title, it.description, it.imageUrl)
        }
    )
}
