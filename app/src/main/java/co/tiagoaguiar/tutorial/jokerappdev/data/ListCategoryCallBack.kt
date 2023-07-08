package co.tiagoaguiar.tutorial.jokerappdev.data

interface ListCategoryCallBack {

    fun onSuccess(response: List<String>) {

    }

    fun onError( response: String) {

    }

    fun onComplete() {

    }
}