package au.com.telstra.data.database

import io.realm.RealmList
import io.realm.RealmObject

open class FactsDao (
    var title: String = "",
    var list: RealmList<FactDao> = RealmList()
): RealmObject()
