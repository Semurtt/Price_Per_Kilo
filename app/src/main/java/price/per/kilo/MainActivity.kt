package price.per.kilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import price.per.kilo.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculate.setOnClickListener {
            val price = binding.price.text.toString().toIntOrNull()
            val weight = binding.weight.text.toString().toIntOrNull()
            if (price != null && weight != null) {
                val result = price.toDouble() / weight.toDouble() * 1000
                binding.result.text = "${result.roundToInt()} руб/кг"
                //binding.result2.text = "$result руб/кг"
                binding.result2.text = "Точная цена ${String.format("%.3f", result)} руб/кг"
                Toast.makeText(this, "Посчитано!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.clear.setOnClickListener {
            binding.price.text.clear()
            binding.weight.text.clear()
            binding.result.text = ""
            binding.result2.text = ""
        }

    }
}
