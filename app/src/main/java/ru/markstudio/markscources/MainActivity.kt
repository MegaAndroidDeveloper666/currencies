package ru.markstudio.markscources

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import ru.markstudio.markscources.adapters.CurrencyAdapter
import ru.markstudio.markscources.data.DataHolder
import ru.markstudio.markscources.data.Response

class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerCurrencies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CurrencyAdapter()
        }
        getCources()
    }

    private fun getCources() {
        progressBar.visibility = View.VISIBLE
        AsyncHttpClient().get(getString(R.string.data_url), responseHandler)
    }

    val responseHandler = object : AsyncHttpResponseHandler() {

        override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
            DataHolder.instance.currencyList = Gson().fromJson(String(responseBody), Response::class.java).stock
            recyclerCurrencies.adapter?.notifyDataSetChanged()
            countDownTimer = object : CountDownTimer(15000, 15000){
                override fun onFinish() {
                    getCources()
                }
                override fun onTick(p0: Long) {}
            }
            countDownTimer.start()
        }

        override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
            Toast.makeText(applicationContext, R.string.loading_error, Toast.LENGTH_LONG).show()
        }

        override fun onFinish() {
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                countDownTimer.cancel()
                getCources()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
