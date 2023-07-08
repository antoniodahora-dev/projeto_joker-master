package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

/*
* MODEL VIEW (VISUALIZAÇÃO) - SAÍDA E ENTRADA - DE USUÁRIO
*/
class HomeFragment : Fragment() {

    private lateinit var progressBar: ProgressBar

    //instancia a class PRESENTERFRAGMENT
    private lateinit var presenter: HomePresenter

    //tornou a variável acessível a todos da class
    private val adapter = GroupieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //conexao com o pacote presentation
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //iremos buscar a recycleView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_bar)

        //recyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_main)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //irá verificar se os itens são igual a zero para não necessitar recargar as info do BD novamente
       if (adapter.itemCount == 0) {

           //evento de ação do package presentation
           presenter.findAllCategories()
       }

        //adapter
        recyclerView.adapter = adapter


        //add items aos adapter - essa chamada não irá mais existe no MODELO MVP
//        adapter.add(CategoryItem(Category("categoria 1", 0xFFF5b73e5)))
//        adapter.add(CategoryItem(Category("categoria 2", 0xFFF8698ec)))
//        adapter.add(CategoryItem(Category("categoria 3", 0xFFFb1bdf3)))
//        adapter.add(CategoryItem(Category("categoria 4", 0xFFFdce2fa)))

        //notifica o adapter de novas informações que foram add.
        //adapter.notifyDataSetChanged()


        //irá navegar para próxima tela
        adapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY, categoryName )

            // transpor dados para outra tela
            findNavController().navigate(R.id.action_nav_home_to_nav_joke , bundle)
        }
    }

    fun showCategories(response: List<Category>) {
        val categories = response.map { CategoryItem(it) }
        adapter.addAll(categories)

        //notifica o adapter de novas informações que foram add.
        adapter.notifyDataSetChanged()
    }

    // irá exibir o progressBar
    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    // irá ocultar o progressBar
    fun hiderProgress() {
        progressBar.visibility = View.GONE
    }

    //message de Error
    fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

// fluxo da aplicação
// 1 . Ciclo de vida do fragment faz ação (Chamar o PRESENTER PEDINDO para BUSCAR as CATEGORIAS)
// 2 . O PRESENTER pede a lista de categorias do MODEL
// 3 . O MODEL devolve uma LISTA list<String>
// 4 . O PRESENTER formatar essa LISTA (STRING) em (CATEGORY (MODEL))
// 5 . VIEW pega LISTA de List<Category> e CONVERTE para LIST<CategoryItem>