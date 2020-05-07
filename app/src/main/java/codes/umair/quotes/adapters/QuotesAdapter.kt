package codes.umair.quotes.adapters

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import codes.umair.quotes.Quote
import codes.umair.quotes.R
import com.google.android.material.snackbar.Snackbar
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

        private val ctx = itemView.context
        private val tvQuote: TextView = itemView.tv_quote
        private val btnCopy: Button = itemView.btn_copy
        private val btnShare = itemView.btn_share
        private var myClipboard: ClipboardManager? =
            ctx.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

        fun bind(quoteObj: Quote) {
            val text = "${quoteObj.quote}\n${quoteObj.author}"
            tvQuote.text = text

            btnShare.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, text)
                ctx.startActivity(Intent.createChooser(shareIntent, "Share View"))
            }
            btnCopy.setOnClickListener {
                val myClip = ClipData.newPlainText("text", text)
                myClipboard?.setPrimaryClip(myClip)
                Snackbar.make(itemView, "Copied to Clipboard!", Snackbar.LENGTH_LONG).show()
            }
        }

    }

}