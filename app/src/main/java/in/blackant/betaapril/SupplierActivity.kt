package `in`.blackant.betaapril

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import `in`.blackant.betaapril.databinding.ActivitySupplierBinding
import `in`.blackant.betaapril.models.Supplier

class SupplierActivity : AppCompatActivity() {
    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
        class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            fun bind(supplier: Supplier) {
                view.findViewById<TextView>(android.R.id.text1).setText(supplier.name)
            }
        }

        private val list = ArrayList<Supplier>()

        fun addItems(items: Array<Supplier>) {
            val start = list.size
            list.addAll(items)
            notifyItemRangeInserted(start, items.size)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = list[position]
            holder.bind(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivitySupplierBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        val listAdapter = Adapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = listAdapter

        binding.add.setOnClickListener { _ ->
            {

            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Api.get(
            this,
            "supplier",
            null,
            null,
            Array<Supplier>::class.java
        ) { suppliers -> listAdapter.addItems(suppliers) }
    }
}