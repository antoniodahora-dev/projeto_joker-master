package co.tiagoaguiar.tutorial.jokerappdev.presentation

import co.tiagoaguiar.tutorial.jokerappdev.data.JokeCallBack
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeDayFragment
import co.tiagoaguiar.tutorial.jokerappdev.data.JokeDayRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke

class JokeDayPresenter(
       private val view: JokeDayFragment,
       private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallBack {

       //metodo
       fun findRandom() {
              view.showProgress()
              dataSource.findRandom(this)
       }

       override fun onSuccess(response: Joke) {
              view.showJoke(response)
       }

       override fun onError(response: String) {
              view.showFailure(response)
       }

       override fun onComplete() {
              view.hiderProgress()
       }

}