package `in`.blackant.betaapril

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.blackant.betaapril.databinding.ActivityMainBinding
import `in`.blackant.betaapril.models.Supplier

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.suppliers.setOnClickListener(this)
        binding.products.setOnClickListener(this)
        binding.orders.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.suppliers -> startActivity(Intent(this, SupplierActivity::class.java))
            R.id.products -> startActivity(Intent(this, ProductActivity::class.java))
            R.id.orders -> startActivity(Intent(this, OrderActivity::class.java))
        }
    }
}