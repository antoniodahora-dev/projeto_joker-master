package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallBack
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.CategoryItem
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeFragment

/*
* MODEL PRESENTER (APRESENTAÇÃO) - Responde as Invocações de Visualização e as Camadas de MODEL
* e formatação dos dados que entram em ambas as camadas.
*/
class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
    ) : ListCategoryCallBack {

    //evento de açao
    fun findAllCategories() {
        view.showProgress() // irá exibir o progressBar
       // fakeRequest()

        dataSource.findAllCategories(this)

    }

    //o servidor deve devolver um viewHolder
    override fun onSuccess(response: List<String>) {

    //        val categories = mutableListOf<CategoryItem>()
    //
    //        for (category in response) {
    //            categories.add(CategoryItem(category))
    //        }


        //gerandos as cores gradiente para o app
        val start = 40 // H - Matiz
        val end = 190 // H - Matiz

        //temos realizar a divisão da COR INICIAL - COR FINAL - depois dividir pela qtd de ELEMENTOS
        // e assim iremos obter o gradiente deseja

        val diff = (end - start) / response.size // 190 - 40 = 150 / 16 = 9.35 (RESULTADO)


        //forma simples de fazer o mesmo código acima
        val categories = response.mapIndexed { index, s ->

            //array de float
            val hsv = floatArrayOf(

                start + (diff * index).toFloat(), // EX.: 40 + (9,37 * 1) -- A ÚNICA A SOFRER A ALTERAÇÃO SERÁ A COR
                100.0f , // S - Saturação 100%
                100.0f  // V - Valor 100%
                )
            Category(s, Color.HSVToColor(hsv).toLong())
        }

        //metodo que irá exibir uma lista de categorias
        view.showCategories(categories)

    }

    override fun onError( response: String) {
        view.showFailure(response)
    }

    // irá ocultar o progressBar
    override fun onComplete() {
        view.hiderProgress()
    }

    //simular uma requesicao web HTTP
   /* private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "categoria 1",
                "categoria 2",
                "categoria 3",
                "categoria 4"
            )

            // lista já está pronta - response

            //irá devolver sucesso
            onSuccess(response)

            //irá devolver erro
            //  onError("Falha na Conexão. Tente novamente mais Tarde!")

            onComplete()
        }, 4000)
    }*/

}