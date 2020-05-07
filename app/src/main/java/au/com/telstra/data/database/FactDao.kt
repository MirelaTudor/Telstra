package au.com.telstra.data.database

import io.realm.RealmObject

open class FactDao (
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null
) : RealmObject()
