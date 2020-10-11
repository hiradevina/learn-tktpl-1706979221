package id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.Grocery
import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.GroceryRepository

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class GroceryViewModel(private val groceryRepository: GroceryRepository)
    : ViewModel() {

    fun getGrocery() = groceryRepository.getGroceries()

    fun addGrocery(grocery: Grocery) = groceryRepository.addGrocery(grocery)
}