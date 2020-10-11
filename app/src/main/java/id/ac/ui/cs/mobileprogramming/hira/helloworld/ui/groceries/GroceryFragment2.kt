package id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import id.ac.ui.cs.mobileprogramming.hira.helloworld.R
import id.ac.ui.cs.mobileprogramming.hira.helloworld.data.Grocery
import id.ac.ui.cs.mobileprogramming.hira.helloworld.utilities.InjectorUtils
import kotlinx.android.synthetic.main.grocery_fragment2_fragment.*

class GroceryFragment2 : Fragment() {

    companion object {
        fun newInstance() = GroceryFragment2()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.grocery_fragment2_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = InjectorUtils.provideGrocerysViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        button_add_grocery.setOnClickListener {
            val grocery = Grocery(editText_jumlah.text.toString().toInt(), editText_name.text.toString())
            viewModel.addGrocery(grocery)
            editText_jumlah.setText("")
            editText_name.setText("")
        }
        // TODO: Use the ViewModel
    }

}
