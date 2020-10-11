package id.ac.ui.cs.mobileprogramming.hira.helloworld.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeGroceryDao {
    private val groceryList = mutableListOf<Grocery>()
    private val groceries = MutableLiveData<List<Grocery>>()

    init {
        groceries.value = groceryList
    }

    fun addGrocery(grocery: Grocery) {
        groceryList.add(grocery)
        groceries.value = groceryList
    }

    fun getGroceries() = groceries as LiveData<List<Grocery>>
}