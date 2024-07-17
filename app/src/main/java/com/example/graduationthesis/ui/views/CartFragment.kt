package com.example.graduationthesis.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationthesis.data.model.ItemCart
import com.example.graduationthesis.databinding.FragmentCartBinding
import com.example.graduationthesis.ui.MainActivity
import com.example.graduationthesis.ui.adapters.ItemCartProductAdapter
import com.example.graduationthesis.ui.viewModel.CartProductViewModel
import com.google.firebase.database.FirebaseDatabase


interface ButtonClickEvent {
    fun clickPlusOrMinusItem(countItem: Int)
    fun updatePriceProduct(totalPrice: Double)

}
class CartFragment : Fragment() , ButtonClickEvent{
    private var listItemCart = mutableListOf<ItemCart>()
    private lateinit var adapterCart : ItemCartProductAdapter
    private lateinit var binding :FragmentCartBinding
    private lateinit var viewModelCartProduct :CartProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCart.layoutManager = LinearLayoutManager(context)
        binding.rvCart.setHasFixedSize(true)
        fun onClickItemProduct(itemCart: ItemCart) {

        }
        adapterCart = ItemCartProductAdapter(::onClickItemProduct)
        binding.rvCart.adapter = adapterCart

        MainActivity.viewModelCart.listCartData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "${it.size}", Toast.LENGTH_SHORT).show()
            binding.numberCart.text = StringBuilder().append("${it.size} SẢN PHẨM")
            adapterCart.updateCartList(it)
        })
        adapterCart.event = this
        viewModelCartProduct = CartProductViewModel()
        viewModelCartProduct.listCartData.observe(this){
            adapterCart.updateTotalPriceProduct()
            binding.edtMoney.text = it.toString()

        }
    }

    override fun clickPlusOrMinusItem(countItem: Int) {
        viewModelCartProduct.updateNumberProduct(countItem)
    }

    override fun updatePriceProduct(totalPrice: Double) {
        viewModelCartProduct.updatePriceProduct(totalPrice)
    }
}