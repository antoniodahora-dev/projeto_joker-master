package co.tiagoaguiar.tutorial.jokerappdev.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAllCategories( callBack: ListCategoryCallBack) {
        //simulando servidor
//        Handler(Looper.getMainLooper()).postDelayed({
//
//            val response = arrayListOf(
//                "categoria 1",
//                "categoria 2",
//                "categoria 3",
//                "categoria 4"
//            )
//
//            Log.i("Teste" , response.toString())
//            // lista já está pronta - response
//
//            //irá devolver sucesso
//            callBack.onSuccess(response)
//
//            //irá devolver erro
//            //  onError("Falha na Conexão. Tente novamente mais Tarde!")
//
//            callBack.onComplete()
//        }, 4000)

        // chamando direto da api
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>>{
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>)
                {
                    //verifica se a resposta for verdadeiro
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callBack.onSuccess(categories ?: emptyList())
                    }
                    // devolve o erro do servidor <500
                    else {
                        val error = response.errorBody()?.string()
                        callBack.onError(error ?: "Erro Desconhecido!" )
                    }

                    //encerra o progressBar
                  callBack.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                   callBack.onError(t.message ?: "Erro Interno")
                   callBack.onComplete()
                }

            })
    }
}