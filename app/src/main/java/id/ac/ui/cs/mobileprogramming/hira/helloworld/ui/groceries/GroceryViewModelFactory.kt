package id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.GroceryRepository

// The same repository that's needed for QuotesViewModel
// is also passed to the factory
class GroceryViewModelFactory(private val groceryRepository: GroceryRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroceryViewModel(groceryRepository) as T
    }
}