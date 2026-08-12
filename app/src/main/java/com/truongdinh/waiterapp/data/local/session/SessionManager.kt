package com.truongdinh.waiterapp.data.local.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.truongdinh.waiterapp.data.datastore.dataStore
import com.truongdinh.waiterapp.data.local.entity.Shift
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    suspend fun saveSession(
        session: UserSession
    ) {
        context.dataStore.edit { prefs ->
            prefs[SessionKey.IS_LOGGED_IN] =
                session.isLoggedIn

            prefs[SessionKey.STAFF_ID] =
                session.staffId

            prefs[SessionKey.STAFF_NAME] =
                session.staffName ?: ""

            prefs[SessionKey.SHIFT] =
                session.shift?.name ?: ""
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    val session: Flow<UserSession> =
        context.dataStore.data.map { prefs ->
            UserSession(
                staffId = prefs[SessionKey.STAFF_ID] ?: 0,

                staffName = prefs[SessionKey.STAFF_NAME],

                shift = prefs[SessionKey.SHIFT]
                    ?.takeIf { it.isNotEmpty() }
                    ?.let {
                        Shift.valueOf(it)
                    },

                isLoggedIn =
                    prefs[SessionKey.IS_LOGGED_IN] ?: false
            )
        }
}