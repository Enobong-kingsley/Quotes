package codes.umair.quotes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import codes.umair.quotes.R
import codes.umair.quotes.adapters.CategoriesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   private val categories = ArrayList<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNames()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.submitList(categories)
        recyclerView.adapter = categoriesAdapter
    }

    private fun loadNames() {
        var list: Array<String>? = null
        try {
            list = assets.list("" + "quotes")
            if (list!!.size > 0) {
                // This is a folder
                for (file in list) {
                    // This is a file
                    categories.add(file.dropLast(5))
                }
            }
        } catch (e: Exception) {
        }
    }

}
