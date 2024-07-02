package com.example.graduationthesis.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationthesis.R
import com.example.graduationthesis.databinding.FragmentBeginCategoryBinding
import com.example.graduationthesis.ui.views.CategoryFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BeginCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BeginCategoryFragment : Fragment() {

    private lateinit var binding : FragmentBeginCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBeginCategoryBinding.inflate(inflater,container,false)
        binding.imgLining.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frmLayout, CategoryFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

}