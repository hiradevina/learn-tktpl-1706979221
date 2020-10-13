package id.ac.ui.cs.mobileprogramming.hira.helloworld.data

// FakegroceryDao must be passed in - it is a dependency
// You could also instantiate the DAO right inside the class without all the fuss, right?
// No. This would break testability - you need to be able to pass a mock version of a DAO
// to the repository (e.g. one that upon calling getgrocery() returns a dummy list of grocery for testing)
// This is the core idea behind DEPENDENCY INJECTION - making things completely modular and independent.
class GroceryRepository private constructor(private val groceryDao: FakeGroceryDao) {

    // This may seem redundant.
    // Imagine a code which also updates and checks the backend.
    fun addGrocery(grocery: Grocery) {
        groceryDao.addGrocery(grocery)
    }

    fun getGroceries() = groceryDao.getGroceries()
    
    fun getSumOfGroceries(): Int? = getGroceries().value?.size

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: GroceryRepository? = null

        fun getInstance(fakeGroceryDao: FakeGroceryDao) =
            instance ?: synchronized(this) {
                instance ?: GroceryRepository(fakeGroceryDao).also { instance = it }
            }
    }
}