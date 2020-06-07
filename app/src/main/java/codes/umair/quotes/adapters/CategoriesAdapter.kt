package codes.umair.quotes.adapters


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import codes.umair.quotes.R
import codes.umair.quotes.activities.QuoteListActivity
import com.skydoves.transformationlayout.TransformationLayout
import kotlinx.android.synthetic.main.cat_item.view.*


class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<String?> = ArrayList()
    lateinit var mActivity: Activity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                holder.bind(items[position], R.color.colorPrimary)
                holder.itemView.cv.setCardBackgroundColor(mActivity.resources.getColor(R.color.colorPrimary))

            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(categoryList: ArrayList<String?>, activity: Activity) {
        items = categoryList
        mActivity = activity
    }

    class CategoryViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val transformationLayout: TransformationLayout = itemView.transformationLayout
        private val categoryName: TextView = itemView.tv_catName
        private val cv = itemView.cv
        private val ctx = itemView.context

        fun bind(category: String?, color: Int) {

            cv.setOnClickListener {
                val openQuotesCategory = Intent(ctx, QuoteListActivity::class.java)
                val bundle = transformationLayout.withContext(ctx, "myTransformAnim")
                openQuotesCategory.putExtra("fileName", category?.capitalize() + ".json")
//                openQuotesCategory.putExtra("color", color)
                openQuotesCategory.putExtra(
                    "TransformationParams",
                    transformationLayout.getParcelableParams()
                )

                ctx.startActivity(openQuotesCategory, bundle)

            }
            categoryName.text = category?.capitalize()


//            val lightness = ColorUtils.calculateLuminance(color)
//            if (lightness > 0.50) {
//                categoryName.setTextColor(Color.BLACK)
//            } else {
//                categoryName.setTextColor(Color.WHITE)
//            }

        }

    }

}