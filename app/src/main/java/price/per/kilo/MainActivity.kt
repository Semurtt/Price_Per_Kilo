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

        val history = mutableListOf(
            "История будущего расчета",
            "История будущего расчета",
            "История будущего расчета",
            "История будущего расчета",
            "История будущего расчета"
        )

        fun fillHistory() {
            binding.firstHistory.text = history[0]
            binding.secondHistory.text = history[1]
            binding.thirdHistory.text = history[2]
            binding.fourthHistory.text = history[3]
            binding.fifthHistory.text = history[4]
        }

        binding.calculate.setOnClickListener {
            val price = binding.price.text.toString().toIntOrNull()
            val weight = binding.weight.text.toString().toIntOrNull()
            if (price != null && weight != null) {
                val result = price.toDouble() / weight.toDouble() * 1000
                //binding.result.text = "${result.roundToInt()} руб/кг"
                binding.result.text = getString(R.string.resultText, result.roundToInt())
                //binding.result2.text = "Точная цена ${String.format("%.3f", result)} руб/кг"
                binding.result2.text =
                    getString(R.string.result2Text, String.format("%.3f", result))
                history.add(
                    0,
                    "Цена ${binding.price.text}р за ${binding.weight.text}гр равна ${binding.result.text}"
                )
                if (history.size > 5) history.removeLast()
                fillHistory()
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
                    binding.weightField.hint = "Пора заказывать Белаз!"
                    binding.weightField.error = "Мы это сами не унесём!"
                    binding.weightField.isErrorEnabled = true
                }
            }
        }

        binding.clear.setOnClickListener {
            Snackbar.make(it, "Сброшено!", Snackbar.LENGTH_SHORT).setTextColor(0XFFFF0000.toInt())
                .setBackgroundTint(0XFF0277BD.toInt()).show()
            binding.price.text = null
            binding.weight.text = null
            binding.result.text = "Цена за килограмм"
            binding.result2.text = "Точная цена"
        }

        binding.clearHistory.setOnClickListener {
            Snackbar.make(it, "История расчетов очищена!", Snackbar.LENGTH_SHORT)
                .setTextColor(0XFFFF0000.toInt())
                .setBackgroundTint(0XFF0277BD.toInt()).show()
            for (i in history.indices) history[i] = "История будущего расчета"
            fillHistory()
        }

    }
}

