package com.waspbyte.equationizer

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.intPreferencesKey
import okio.Path.Companion.toPath

typealias PrefsDataStore = DataStore<Preferences>
fun createDataStore(producePath: () -> String): PrefsDataStore =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )


const val dataStoreFileName = "equationizer.preferences_pb"
val HIGH_SCORE_KEY = intPreferencesKey("high_score");
