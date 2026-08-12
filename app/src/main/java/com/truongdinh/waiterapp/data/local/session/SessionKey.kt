package com.truongdinh.waiterapp.data.local.session

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SessionKey {
    val IS_LOGGED_IN =
        booleanPreferencesKey("is_logged_in")

    val STAFF_ID =
        intPreferencesKey("staff_id")

    val STAFF_NAME =
        stringPreferencesKey("staff_name")

    val SHIFT =
        stringPreferencesKey("shift")
}