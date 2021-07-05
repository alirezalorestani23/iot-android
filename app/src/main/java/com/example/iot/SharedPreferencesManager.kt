package com.example.iot

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var isFirstLaunch: Boolean

    fun clearShredPref()
}

@SuppressLint("CommitPrefEdits")
class SharedPreferencesManager(private val context: Context) : PreferenceStorage {

    private val sharedPreferences = lazy {
        context.getSharedPreferences(
            "IOT_PROJECT",
            Context.MODE_PRIVATE
        )
    }

    override var isFirstLaunch: Boolean by BooleanPreference(
        preferences = sharedPreferences,
        name = IS_FIRST_LAUNCH,
        defaultValue = true
    )

    override fun clearShredPref() {
        sharedPreferences.value.edit().clear()
    }

    companion object {
        private const val IS_FIRST_LAUNCH = "IFL"
        private const val APP_CONFIG_NAME = "ACN"
        private const val FIRE_BASE_TOKEN = "FBT"
        private const val LANGUAGE = "LAN"
        private const val LAST_TIMEZONE_USED = "LTU"
    }
}

class BooleanPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}

class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.value.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.value.edit { putString(name, value) }
    }
}