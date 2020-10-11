package id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.hira.helloworld.R
import id.ac.ui.cs.mobileprogramming.hira.helloworld.utilities.InjectorUtils


class GroceryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocery)
        val fragMan: FragmentManager = supportFragmentManager
        val fragTransaction: FragmentTransaction = fragMan.beginTransaction()
        val groceryFragment : GroceryFragment2 = GroceryFragment2()
        fragTransaction.add(R.id.root_layout, groceryFragment)
        fragTransaction.commit()
        initializeUi()
    }

    private fun initializeUi() {
        // Get the grocerysViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideGrocerysViewModelFactory()
        // Use ViewModelProviders class to create / get already created grocerysViewModel
        // for this view (activity)
        val viewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)

        // Observing LiveData from the grocerysViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getGrocery().observe(this, Observer { grocery ->
            if (grocery.isNotEmpty()) {
                val bundle = Bundle()
                bundle.putString("data", grocery.last().toString())
                val fragMan: FragmentManager = supportFragmentManager

                val listFragment : GroceryFragment = GroceryFragment()
                listFragment.arguments = bundle
                val transaction = fragMan.beginTransaction().replace(R.id.root_layout, listFragment).addToBackStack(null)
                transaction.commit()

            }

        })
    }


}