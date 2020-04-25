package org.d3if1071.idealku.viewmodel

import android.media.Image
import android.view.View
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if1071.idealku.R
import kotlin.math.pow

class MainViewModel : ViewModel(){
    private val _im_BMI=MutableLiveData<Int>()
    private val _hasilBMI=MutableLiveData<String>()
    private val _hasilIdeal=MutableLiveData<Double>()

    val im_BMI:LiveData<Int>get()= _im_BMI
    val hasilBMI:LiveData<String>get()= _hasilBMI
    val hasilIdeal:LiveData<Double>get()= _hasilIdeal




    init {
        _hasilIdeal.value=0.0
        _hasilBMI.value=""
        _im_BMI.value=R.drawable.gemuk
    }


    fun hitung(tinggi : Double,berat:Double,laki : RadioButton,perempuan:RadioButton,tvjudul1:TextView,tvjudul2:TextView,tvjudul3:TextView,tvHasil1:TextView,tvHasil2:TextView,imBMI:
    ImageView,imBerat:ImageView,bSimpanData:Button){
        val tinggiHasil = (tinggi / 100).pow(2)
        val hitungBMI =berat/tinggiHasil

        if (hitungBMI <=18.5){
            _hasilBMI.value="Kurus"
            _im_BMI.value= R.drawable.kurus

        }else if (hitungBMI >18.5&&hitungBMI<25){
            _hasilBMI.value="Normal"
            _im_BMI.value= R.drawable.ideal
        }else if (hitungBMI>=25&&hitungBMI<30){
            _hasilBMI.value="Gemuk"
            _im_BMI.value= R.drawable.gemuk
        }else{
            _hasilBMI.value="Obesitas"
            _im_BMI.value= R.drawable.gemuk
        }

        var hasilBeratIdeal:Double=0.0
        if (laki.isChecked){
            hasilBeratIdeal=(tinggi-100)-((tinggi-100)*0.1)
        }else if (perempuan.isChecked){
            hasilBeratIdeal=(tinggi-100)-((tinggi-100)*0.5)
        }
        _hasilIdeal.value=hasilBeratIdeal

        tvHasil1.visibility= View.VISIBLE
        tvHasil2.visibility= View.VISIBLE
        tvjudul1.visibility= View.VISIBLE
        tvjudul2.visibility= View.VISIBLE
        tvjudul3.visibility= View.VISIBLE
        imBMI.visibility= View.VISIBLE
        imBerat.visibility= View.VISIBLE
        bSimpanData.visibility= View.VISIBLE
    }

    fun reset(rblaki:RadioButton,etTinggi:EditText,etBerat:EditText,tvjudul1:TextView,tvjudul2:TextView,tvjudul3:TextView,tvHasil1:TextView,tvHasil2:TextView,imBMI:
    ImageView,imBerat:ImageView,bSimpanData:Button){
        rblaki.isChecked=true
        etTinggi.text=null
        etBerat.text=null
        _hasilBMI.value=""
        tvHasil1.visibility= View.GONE
        tvHasil2.visibility= View.GONE
        tvjudul1.visibility= View.GONE
        tvjudul2.visibility= View.GONE
        tvjudul3.visibility= View.GONE
        imBMI.visibility= View.GONE
        imBerat.visibility= View.GONE
        bSimpanData.visibility= View.GONE

    }


}