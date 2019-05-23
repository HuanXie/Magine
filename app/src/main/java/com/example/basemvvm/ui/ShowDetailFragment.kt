package com.example.basemvvm.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemvvm.R
import com.example.basemvvm.data.datamodels.ShowInfo
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_show_details.detailExample
import javax.inject.Inject

class ShowDetailFragment : DaggerDialogFragment(){

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_show_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val details = mainViewModel.showInfo.value as List<ShowInfo>
        detailExample.text = details[mainViewModel.selectPosition].show?.summary
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.BOTTOM)
        }
    }
}
