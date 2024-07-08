package com.example.graduationthesis.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.graduationthesis.data.model.lining.ProductLining
import com.example.graduationthesis.databinding.FragmentCategoryBinding
import com.example.graduationthesis.ui.GUI.DetailProductLiningActivity
import com.example.graduationthesis.ui.adapters.liningAdapter.CategorysLiningAdapter
import com.example.graduationthesis.ui.viewModel.ProductLiningViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    private lateinit var adapter: CategorysLiningAdapter
    private lateinit var viewModel: ProductLiningViewModel
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var itemDecoration: ItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        adapter = CategorysLiningAdapter(::onItemClick)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMainProduct.layoutManager = LinearLayoutManager(context)
        binding.rvMainProduct.setHasFixedSize(true)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvMainProduct.addItemDecoration(itemDecoration)
        adapter = CategorysLiningAdapter(::onItemClick)
        binding.rvMainProduct.adapter = adapter

        viewModel = ViewModelProvider(this)[ProductLiningViewModel::class.java]
        viewModel.parentCategory.observe(viewLifecycleOwner, Observer {
            adapter.updateCategorys(it)
        })

    }

    private fun onItemClick(itemResponse: ProductLining) {

        var intent = Intent(context,DetailProductLiningActivity::class.java).apply {
            putExtra("namePD",itemResponse.namePD)
            putExtra("urlPD",itemResponse.urlPD)
            putExtra("pricePD",itemResponse.pricePD)
            putExtra("titlePD",itemResponse.titlePD)
            putExtra("colorPD",itemResponse.colorPD)
            putExtra("sizePD",itemResponse.sizePD)
        }
        startActivity(intent)

        Toast.makeText(context, "${itemResponse.namePD}", Toast.LENGTH_LONG).show()
    }
}