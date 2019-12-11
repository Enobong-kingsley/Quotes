package codes.umair.quotes.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import codes.umair.quotes.R
import codes.umair.quotes.adapters.QuotesAdapter
import codes.umair.quotes.model.Quote
import kotlinx.android.synthetic.main.activity_quote_list.*
import org.json.JSONArray
import org.json.JSONException
import java.io.InputStream

class QuoteListActivity : AppCompatActivity() {
    private val quotes = ArrayList<Quote>()
    private var title: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)
        setSupportActionBar(toolbar)
        title = intent.getStringExtra("fileName")?.dropLast(5)
        setTitle(title)
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
        }
    }

    private fun readJsonFromAsset(): String? {
        val fileName = intent.getStringExtra("fileName")
        var json: String?
        try {
            val inputStream: InputStream = assets.open(fileName)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}
