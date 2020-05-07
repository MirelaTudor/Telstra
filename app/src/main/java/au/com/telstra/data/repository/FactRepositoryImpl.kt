package au.com.telstra.data.repository

import au.com.telstra.data.database.FactDao
import au.com.telstra.data.database.FactsDao
import au.com.telstra.data.domain.FactRepository
import au.com.telstra.data.domain.mapper.FactEntityMapper
import au.com.telstra.data.domain.model.FactEntityList
import au.com.telstra.data.remote.FactService
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * A repository class, used to collect the data from networking or from cache
 */
class FactRepositoryImpl @Inject constructor(
    private val factService: FactService,
    private val factEntityMapper: FactEntityMapper
) : FactRepository {

    override suspend fun getFacts() = callbackFlow {
        Realm.getDefaultInstance().use { realm ->
            val query = realm.where(FactsDao::class.java).findAll()
            if (query.isNotEmpty()) {
                query.first()?.let { offer(factEntityMapper.mapFromLocal(it)) }
            }

            query.addChangeListener { result ->
                result.first()?.let {
                    offer(factEntityMapper.mapFromLocal(it))
                }
            }

            awaitClose {
                query.removeAllChangeListeners()
            }
        }
    }

    @Throws(Exception::class)
    override suspend fun refreshFacts() {
        val remoteData = factService.getFacts()
        return if (remoteData.isSuccessful) {
            val data = remoteData.body()
            if (data != null) {
                persistData(factEntityMapper.mapFromRemote(data))
            }  else  {
                throw NullPointerException()
            }
        } else {
            throw IllegalStateException()
        }
    }

    private fun persistData(entityList: FactEntityList){
        Realm.getDefaultInstance().use { realm ->
            realm.beginTransaction()
            realm.deleteAll()

            val managedListFacts = realm.copyToRealm(
                entityList
                    .facts
                    .map {
                        FactDao(it.title, it.description, it.imageUrl)
                    }
            )

            val fact = realm.createObject<FactsDao>()
            fact.title = entityList.title
            fact.list.addAll(managedListFacts)

            realm.commitTransaction()
        }
    }
}

