package org.d3if1071.idealku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if1071.idealku.R
import org.d3if1071.idealku.convertWaktu
import org.d3if1071.idealku.data.DataBeratBadan
import org.d3if1071.idealku.databinding.ListdataberatbadanBinding

class BeratBadanAdapter (private val listBeratBadan:List<DataBeratBadan>):RecyclerView.Adapter<BeratBadanAdapter.BeratBadanViewHolder>(){
    var listener:RecyclerViewOnClick?=null
    inner class BeratBadanViewHolder (
        val listdataberatbadanBinding: ListdataberatbadanBinding
    ):RecyclerView.ViewHolder(listdataberatbadanBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=BeratBadanViewHolder (
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.listdataberatbadan,parent,false)
    )

    override fun getItemCount()=listBeratBadan.size

    override fun onBindViewHolder(holder: BeratBadanViewHolder, position: Int) {
        holder.listdataberatbadanBinding.tvDate.text= convertWaktu(listBeratBadan[position].tanggal)
        holder.listdataberatbadanBinding.tvHasilBMI.text = "Hasil BMI : "+listBeratBadan[position].hasilBMI
        holder.listdataberatbadanBinding.tvBerat.text="Berat Badan : "+listBeratBadan[position].berat
        holder.listdataberatbadanBinding.tvHasilberatIdealList.text="Berat Ideal : "+listBeratBadan[position].hasilBeratBadanIdeal
        holder.listdataberatbadanBinding.tvTinggi.text="Tinggi Badan : "+listBeratBadan[position].tinggi
        holder.listdataberatbadanBinding.tvJenisKelamin.text="Jenis Kelamin : "+listBeratBadan[position].jenisKelamin

        holder.listdataberatbadanBinding.listData.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it,listBeratBadan[position])
        }
    }

}