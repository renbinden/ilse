package uk.co.renbinden.ilse.asset


abstract class Asset {

    private val loadListeners = mutableListOf<() -> Unit>()

    abstract val isLoaded: Boolean
    fun addLoadListener(listener: () -> Unit) {
        loadListeners.add(listener)
    }

    protected fun onLoad() {
        loadListeners.forEach { it.invoke() }
    }

}