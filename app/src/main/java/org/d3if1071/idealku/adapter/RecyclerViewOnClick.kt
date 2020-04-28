package org.d3if1071.idealku.adapter

import android.view.View
import org.d3if1071.idealku.data.DataBeratBadan


interface RecyclerViewOnClick {
    fun onRecyclerViewItemClicked(view: View, dataBeratBadan: DataBeratBadan)
}