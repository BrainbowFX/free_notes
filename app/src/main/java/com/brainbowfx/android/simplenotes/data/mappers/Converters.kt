package com.brainbowfx.android.simplenotes.data.mappers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toString(input: List<String>): String {
            return Gson().toJson(input)
        }

        @TypeConverter
        @JvmStatic
        fun fromString(input: String): List<String> {
            val type = object : TypeToken<List<String>>() { }.type
            return Gson().fromJson(input, type)
        }
    }
}