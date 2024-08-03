package `in`.blackant.betaapril

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.blackant.betaapril.databinding.SupplierListItemBinding
import `in`.blackant.betaapril.models.Supplier

class SupplierAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<SupplierAdapter.ViewHolder>() {
    private val list = ArrayList<Supplier>()

    fun add(supplier: Supplier) {
        list.add(supplier)
        notifyItemInserted(itemCount)
    }

    fun addAll(suppliers: Array<Supplier>) {
        val start = itemCount
        list.addAll(suppliers)
        notifyItemRangeInserted(start, suppliers.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SupplierListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(private val binding: SupplierListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(supplier: Supplier) {
            binding.name.text = supplier.name
        }
    }
}