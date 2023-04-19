package com.example.githubtest.data.network.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject


class ModelUtils(private val builder : GsonBuilder) {

    @Suppress("unused")
    fun datePattern(pattern : String?) : ModelUtils {
        builder.setDateFormat(pattern)
        return this
    }

    @Suppress("unused")
    fun serializeNulls(serialize : Boolean) : ModelUtils {
        if (serialize) builder.serializeNulls()
        return this
    }

    @Suppress("unused")
    fun excludeFieldsWithoutExpose(exclude : Boolean) : ModelUtils {
        if (exclude) builder.excludeFieldsWithoutExposeAnnotation()
        return this
    }

    fun build() : Gson {
        return builder.create()
    }

    private fun toJsonString(any : Any) : String {
        return build().toJson(any)
    }

    @Suppress("unused")
    @Throws(JSONException::class)
    fun toJsonObject(any : Any) : JSONObject {
        return JSONObject(toJsonString(any))
    }


    inline fun <reified T> toModel(json : String) : T? {
        return try {
            val typeToken = object : TypeToken<T>() {}.type
            build().fromJson(json , typeToken)
        } catch (e : JsonSyntaxException) {
            null
        }
    }

    @Suppress("unused")
    inline fun <reified T> toModelList(json : String) : List<T>? {
        return if (json.isEmpty()) null else try {
            val typeToken = object : TypeToken<List<T>>() {}.type
            build().fromJson(json , typeToken)
        } catch (e : JsonSyntaxException) {
            ArrayList()
        }
    }

}
