package ru.markstudio.markscources.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_currency.view.*
import ru.markstudio.markscources.R
import ru.markstudio.markscources.data.Currency
import ru.markstudio.markscources.data.DataHolder
import java.math.RoundingMode

class CurrencyAdapter() : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))
    }

    override fun getItemCount() = DataHolder.instance.currencyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val currency = DataHolder.instance.currencyList[position]
            itemView.currencyName.text = currency.name
            itemView.currencyVolume.text = currency.volume.toString()
            itemView.currencyPrice.text = currency.price.amount.setScale(2, RoundingMode.HALF_UP).toString()
        }
    }
}