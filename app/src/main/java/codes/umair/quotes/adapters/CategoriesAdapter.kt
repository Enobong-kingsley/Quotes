package codes.umair.quotes.adapters


import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import codes.umair.quotes.R
import codes.umair.quotes.activities.QuoteListActivity
import kotlinx.android.synthetic.main.cat_item.view.*
import kotlin.random.Random


class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<String?> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                holder.bind(items[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList: ArrayList<String?>) {
        items = categoryList
    }

    class CategoryViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val categoryName: TextView = itemView.tv_catName
        private val cv = itemView.cv
        private val ctx = itemView.context
        fun bind(category: String?) {
            cv.setOnClickListener {
                val openQuotesCategory = Intent(ctx, QuoteListActivity::class.java)
                openQuotesCategory.putExtra("fileName", category?.capitalize() + ".json")
                ctx.startActivity(openQuotesCategory)
            }
            categoryName.text = category?.capitalize()

            val color = Color.rgb(Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
            cv.setCardBackgroundColor(color)
            val lightness = ColorUtils.calculateLuminance(color)
            if (lightness > 0.50) {
                categoryName.setTextColor(Color.BLACK)
            } else {
                categoryName.setTextColor(Color.WHITE)
            }

        }

    }

}