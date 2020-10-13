package id.ac.ui.cs.mobileprogramming.hira.helloworld.utilities

import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.FakeDatabase
import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.GroceryRepository
import id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries.GroceryViewModelFactory

object InjectorUtils {
    fun provideGrocerysViewModelFactory(): GroceryViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val groceryRepository = GroceryRepository.getInstance(FakeDatabase.getInstance().groceryDao)
        return GroceryViewModelFactory(groceryRepository)
    }
}