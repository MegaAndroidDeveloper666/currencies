package ru.markstudio.markscources.data

class DataHolder private constructor() {
    private object Holder {
        val INSTANCE = DataHolder()
    }

    companion object {
        val instance: DataHolder by lazy { Holder.INSTANCE }
    }

    var currencyList: ArrayList<Currency> = ArrayList()
}