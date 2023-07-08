package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {

    //CAMINHO DO BD
    @GET("jokes/categories")

    //PASSAMOS AQUI O CAMINHO DA CHAVE DE ACESSO A NOSSA API
    fun findAllCategories(@Query("apiKey") apiKey: String = HTTPClient.API_KEY) : Call<List<String>>

    //CAMINHO DO BD para as piadas
    @GET("jokes/random")
    fun findRandom(@Query("category") categoryName: String? = null, @Query("apiKey") apiKey: String = HTTPClient.API_KEY) : Call<Joke>
}