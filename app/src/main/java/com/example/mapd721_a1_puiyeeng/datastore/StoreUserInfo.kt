package com.example.mapd721_a1_puiyeeng.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserInfo (private val context: Context) {

    // Companion object to create a single instance of DataStore for user username, email and id
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserInfo")

        // Keys to uniquely identify user username, email and id in DataStore
        val USER_USERNAME_KEY = stringPreferencesKey("user_username")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_ID_KEY = stringPreferencesKey("user_id")
    }

    // Flow representing the user's stored username
    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored username value or return an empty string if not present
            preferences[USER_USERNAME_KEY] ?: ""
        }

    // Flow representing the user's stored email
    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored email value or return an empty string if not present
            preferences[USER_EMAIL_KEY] ?: ""
        }

    // Flow representing the user's stored id
    val getId: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored id value or return an empty string if not present
            preferences[USER_ID_KEY] ?: ""
        }

    // Function to save user username, email and id in DataStore
    suspend fun saveInfo(username: String, email: String, id: String) {
        // Use the DataStore's edit function to make changes to the stored preferences
        context.dataStore.edit { preferences ->
            // Update the user username, email and id values in the preferences
            preferences[USER_USERNAME_KEY] = username
            preferences[USER_EMAIL_KEY] = email
            preferences[USER_ID_KEY] = id
        }
    }

    suspend fun clearInfo() {
        context.dataStore.edit { preferences ->
            // Clear the info in the stored preferences
            preferences[USER_USERNAME_KEY] = ""
            preferences[USER_EMAIL_KEY] = ""
            preferences[USER_ID_KEY] = ""
        }
    }
}