package com.debtdestroyer.android.utils

import android.content.Context
import com.debtdestroyer.android.model.ScholarshipModel
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

class JsonUtils {

}
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun getListOfItems(context: Context): List<ScholarshipModel> {
    val jsonFileString = getJsonDataFromAsset(context, "scholarship.json")

    val gson = Gson()
    val listPersonType = object : TypeToken<List<ScholarshipModel>>() {}.type

    //persons.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$person") }
    return gson.fromJson(jsonFileString, listPersonType)

}