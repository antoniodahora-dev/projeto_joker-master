package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokeDayPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeDayFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView


    //camada de apresentacao
    private lateinit var presenter: JokeDayPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokeDayPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_joke_day, container, false)
    }

    //devemos criar o onViewCreated para pegar do FRAGMENT_HOME e passar para o FRAGMENT_JOKE
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // Log.d("Teste", categoryDay.toString()) // log de teste do logCat

        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = getString(R.string.menu_joke_day) // o nome da Categoria será exibida no Fragment_Joke

        //components do fragment_joke (layout)
        progressBar = view.findViewById(R.id.progress_bar)
        textView = view.findViewById(R.id.txt_joke)
        imageView = view.findViewById(R.id.img_joke)

        //vamos realizar uma requisiçao pela piada do DIa
        presenter.findRandom()
    }

    //ira devolver o objeto data class JOKE
    fun showJoke(joke: Joke) {

        //iremos pegar o textView um novo texto(piada)
        textView.text = joke.text
        Picasso.get().load(joke.iconUrl).into(imageView) // download da imagem através do PICASSO
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