package id.ac.ui.cs.mobileprogramming.hira.helloworld.ui.groceries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.ac.ui.cs.mobileprogramming.hira.helloworld.R
import kotlinx.android.synthetic.main.fragment_grocery.*
import kotlinx.android.synthetic.main.grocery_fragment2_fragment.*


/**
 * A simple [Fragment] subclass.
 * Use the [GroceryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroceryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var groceries: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            groceries = it.getString("data")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grocery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView_grocery.text = groceries
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment grocery.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            GroceryFragment().apply {
                arguments = Bundle().apply {
                    putString("data", param1)
                }
            }
    }
}
