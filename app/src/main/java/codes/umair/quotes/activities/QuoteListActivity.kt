package codes.umair.quotes.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import codes.umair.quotes.Quote
import codes.umair.quotes.R
import codes.umair.quotes.adapters.QuotesAdapter
import com.skydoves.transformationlayout.onTransformationEndContainer
import kotlinx.android.synthetic.main.activity_quote_list.*
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream

class QuoteListActivity : AppCompatActivity() {
    private val quotes = ArrayList<Quote>()
    private var title: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationEndContainer(intent.getParcelableExtra("TransformationParams"))
//        val cardColor = intent.getIntExtra("color", 0)
//        val lightness = ColorUtils.calculateLuminance(cardColor)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)
        setSupportActionBar(toolbar)

        title = intent.getStringExtra("fileName")?.dropLast(5)


        cv.setCardBackgroundColor(resources.getColor(R.color.colorPrimary))
        window.statusBarColor = resources.getColor(R.color.colorPrimary)
        window.navigationBarColor = resources.getColor(R.color.colorPrimary)

        setTitle(title)
//
//
//        if (lightness > 0.50) {
//            toolbar_layout.apply {
//                setCollapsedTitleTextColor(Color.BLACK)
//                setExpandedTitleColor(Color.BLACK)
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
//            }
//        } else {
//            toolbar_layout.apply {
//                setCollapsedTitleTextColor(Color.WHITE)
//                setExpandedTitleColor(Color.WHITE)
//            }
//        }


        getQuotes()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView_quotes.layoutManager = LinearLayoutManager(this)
        val quotesAdapter = QuotesAdapter()
        quotesAdapter.submitQuoteList(quotes)
        recyclerView_quotes.adapter = quotesAdapter
    }

    private fun getQuotes() {
        val jSONArray: JSONArray
        try {
            jSONArray = JSONArray(readJsonFromAsset())
            for (i in 0 until jSONArray.length()) {
                val jSONObject = jSONArray.getJSONObject(i)
                val quote = jSONObject.getString("quote")
                val author = jSONObject.getString("author")
                val quoteObj = Quote(quote.replace("&#39;", "'"), author)
                quotes.add(quoteObj)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Toast.makeText(this@QuoteListActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun readJsonFromAsset(): String? {
        val fileName = intent.getStringExtra("fileName")
        lateinit var json: String
        try {
            val inputStream: InputStream = assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this@QuoteListActivity, e.localizedMessage, Toast.LENGTH_LONG).show()
            return null
        }
        return json
    }

}