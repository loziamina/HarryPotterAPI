package com.amonteiro.a01_supvincia.model

import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    var searchText = mutableStateOf("")
    val res = WeatherAPI.loadWeather(searchText.value)
    println(res)
}

object WeatherAPI {
    val client = OkHttpClient()
    val gson = Gson()

    const val URL_API = "https://hp-api.onrender.com/api/characters"

    fun loadWeather(CharactersName : String): WeatherBean {
        val json = sendGet(URL_API.format(CharactersName))
        return gson.fromJson(json, WeatherBean::class.java)
    }


    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

/* -------------------------------- */
// Bean
/* -------------------------------- */

data class WeatherBean(var main : TempBean, var wind:WindBean, var name:String, var weather:List<DescriptionBean> )
data class TempBean(var temp : Double)
data class WindBean(var speed : Double)
data class DescriptionBean(
    val actor: String,
    val id: String,
    val image: String,
    val name: String,
    val dateOfBirth: String,
    val yearOfBirth: Int
)

