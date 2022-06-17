package price.per.kilo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
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
                binding.result2.text = "Точная цена ${String.format("%.3f", result)} руб/кг"
                Toast.makeText(this, "Посчитано!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Ошибка!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.price.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.length < 7) {
                    binding.priceField.hint = "Введите цену товара, указанную на ценнике"
                    binding.priceField.isErrorEnabled = false
                } else {
                    binding.priceField.hint = "Квартиру что ли покупаем?"
                    binding.priceField.error = "Цифр поменьше набирайте!"
                    binding.priceField.isErrorEnabled = true
                }
            }
        }

        binding.weight.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.length < 7) {
                    binding.weightField.hint = "Введите вес в граммах c товара"
                    binding.weightField.isErrorEnabled = false
                } else {
                    binding.weightField.hint = "Холодильник взвешиваем?"
                    binding.weightField.error = "Мы это сами не унесём!"
                    binding.weightField.isErrorEnabled = true
                }
            }
        }

        binding.clear.setOnClickListener {
            Snackbar.make(it, "Сброшено!", Snackbar.LENGTH_SHORT).setTextColor(0XFF0277BD.toInt()).setBackgroundTint(0XFF81C784.toInt()).show()
            binding.price.text = null
            binding.weight.text = null
            binding.result.text = ""
            binding.result2.text = ""
        }

    }
}

