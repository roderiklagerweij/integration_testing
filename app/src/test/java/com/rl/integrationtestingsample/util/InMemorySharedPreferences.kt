package com.rl.integrationtestingsample.util

import android.content.SharedPreferences

class InMemorySharedPreferences : SharedPreferences {

    val map = HashMap<String, Any>()

    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(key: String?, defValue: String?): String? {
        return map[key] as String?
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        TODO("Not yet implemented")
    }

    override fun getInt(key: String?, defValue: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(key: String?, defValue: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(key: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun edit(): SharedPreferences.Editor {
        return object : SharedPreferences.Editor {
            override fun putString(key: String?, value: String?): SharedPreferences.Editor {
                if (key != null && value != null)
                    map.put(key, value)
                return this
            }

            override fun putStringSet(
                key: String?,
                values: MutableSet<String>?
            ): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun remove(key: String?): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun clear(): SharedPreferences.Editor {
                map.clear()
                return this
            }

            override fun commit(): Boolean = true

            override fun apply() { }
        }
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

}