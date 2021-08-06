package com.example.customviewcurrency

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.example.customviewcurrency.databinding.CustomCurrencyViewBinding

class CurrencyEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomCurrencyViewBinding =
        CustomCurrencyViewBinding.inflate(LayoutInflater.from(context), this)

    private var signCurrency: String = ""
        set(value) {
            field = value
            binding.currencyText.text = field
        }

    private var limitNumber: Int = 0

    init {
        loadAttr(attrs, defStyleAttr)

        binding.currencyEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.currencyEditText.text.isNotEmpty()) {

                    val value: Double = (binding.currencyEditText.text.toString()).toDouble()

                    if (value > limitNumber && limitNumber != 0) {
                        setBackgroundResource(R.drawable.currency_shape_error)
                        binding.currencyEditText.setTextColor(resources.getColor(R.color.red_dark))
                    } else {
                        setBackgroundResource(R.drawable.currency_shape)
                        binding.currencyEditText.setTextColor(resources.getColor(R.color.blue))
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {

        context.withStyledAttributes(attrs, R.styleable.CurrencyEditText, defStyleAttr, 0) {
            signCurrency = getString(R.styleable.CurrencyEditText_sign).toString()
            limitNumber = getInt(R.styleable.CurrencyEditText_limit_number, 0)
            isEnabled = getBoolean(R.styleable.CurrencyEditText_android_enabled, false)
        }
    }

    fun setLimit(number: Int) {
        limitNumber = number
    }

    fun changeSignCurrency(sign: String) {
        signCurrency = sign
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

       if(enabled) {
            binding.currencyEditText.isEnabled = true
            setBackgroundResource(R.drawable.currency_shape)
            alpha = 1F
        }
        else {
           binding.currencyEditText.isEnabled = false
           setBackgroundResource(R.drawable.currency_shape)
           alpha = 0.5F
       }
    }
}