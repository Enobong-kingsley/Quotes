package codes.umair.quotes.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import codes.umair.quotes.R
import codes.umair.quotes.adapters.CategoriesAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.skydoves.transformationlayout.onTransformationStartContainer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private val categories = ArrayList<String?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNames()
        initRecyclerView()

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.submitList(categories, this@MainActivity)
        recyclerView.adapter = categoriesAdapter
    }

    private fun loadNames() {
        var list: Array<String>?
        try {
            list = assets.list("")
            if (list!!.isNotEmpty()) {
                for (file in list) {
                    // This is a file
                    if (file.contains(".json"))
                        categories.add(file.dropLast(5))
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

}