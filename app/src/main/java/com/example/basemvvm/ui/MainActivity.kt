package com.example.basemvvm.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basemvvm.R
import com.example.basemvvm.data.datamodels.ShowInfo
import com.example.basemvvm.extensions.setVisibleOrGone
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.loadingProgressBar
import kotlinx.android.synthetic.main.activity_main.mainActivityView
import kotlinx.android.synthetic.main.activity_main.mainViewSearchView
import kotlinx.android.synthetic.main.activity_main.mainViewShowList
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog


/**
 * Main Activity will show the list of shows.
 *
 * NOTICE: these files in network folder and the NetworkModule are useless
 * due to the retrofit can't handle the http connection to api.tvmaze.com
 * But the developer still leave them there to save as an example.
 */
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val showInfoList = mutableListOf<ShowInfo>()
    private val showInfoListAdapter = ShowInfoListAdapter(showInfoList) { position ->
        mainViewModel.selectPosition = position
        ShowDetailFragment().show(supportFragmentManager, DETAIL_FRAGMENT_TAG)
    }
    private var queryString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    queryString = query
                    getShows(query)
                    hideKeyBoard()
                    return true
                }
                return false
            }
        })

        mainViewModel.showLoading.observe(this, Observer {
            loadingProgressBar.setVisibleOrGone(it)
        })

        // To Test the error dialog, you can turn off the wifi and mobile data to try.
        mainViewModel.showError.observe(this, Observer { show ->
            if (show) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(getString(R.string.retry_title))
                alertDialogBuilder
                    .setPositiveButton(R.string.retry) { _, id ->
                        getShows(queryString)
                    }
                    .setNegativeButton(R.string.dismiss) { dialog, _ ->
                        dialog.dismiss()
                    }

                alertDialogBuilder.create().show()
            }
        })

    }

    override fun onResume() {
        super.onResume()
        mainViewModel.showInfo.observe(this, Observer {
            showInfoList.clear()
            showInfoList.addAll(it)
            showInfoListAdapter.notifyDataSetChanged()
        })
        mainViewShowList.adapter = showInfoListAdapter
        mainViewShowList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun hideKeyBoard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mainActivityView.windowToken, 0)
    }

    private fun getShows(query: String) {
        mainViewModel.getShowList(query)
        mainViewModel.adjustLoadingIcon(true)
    }

    companion object {
        private const val DETAIL_FRAGMENT_TAG = "detail_fragment"
    }

}
