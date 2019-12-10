package codes.umair.quotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import codes.umair.quotes.Quote
import codes.umair.quotes.R
import kotlinx.android.synthetic.main.quote_item.view.*


class QuotesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<Quote> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is QuoteViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitQuoteList(quoteList: ArrayList<Quote>) {
        items = quoteList
    }

    class QuoteViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvQuote: TextView = itemView.tv_quote
        fun bind(quoteObj: Quote) {
            val text = "${quoteObj.quote}\n${quoteObj.author}"
            tvQuote.text = text
        }

    }

}