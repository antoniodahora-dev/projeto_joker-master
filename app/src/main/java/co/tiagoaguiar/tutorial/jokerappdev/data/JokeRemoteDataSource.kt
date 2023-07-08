package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class JokeRemoteDataSource {

    //metodo irá nos devolver o JSON já formatado
    fun findBy(categoryName: String, callBack: JokeCallBack) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom(categoryName) // pegando a informação da API
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {

                    //verifica se a resposta for verdadeiro
                    if (response.isSuccessful) {
                        val jokeDay = response.body()
                        callBack.onSuccess(jokeDay ?: throw RuntimeException("Piada não encontrada!"))
                    }

                    // devolve o erro do servidor <500
                    else {
                        val error = response.errorBody()?.string()
                        callBack.onError(error ?: "Erro Desconhecido!" )
                    }

                    //encerra o progressBar
                    callBack.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callBack.onError(t.message ?: "Erro Interno")
                    callBack.onComplete()
                }

            })

    }


}