package co.tiagoaguiar.tutorial.jokerappdev.model

import com.google.gson.annotations.SerializedName

//objeto de dados
data class Joke (

    //identificacao da informacao que vem da API
    @SerializedName("value") val text: String,
    @SerializedName("icon_url") val iconUrl: String
)