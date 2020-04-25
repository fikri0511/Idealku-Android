package org.d3if1071.idealku.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import org.d3if1071.idealku.R
import org.d3if1071.idealku.viewmodel.MainViewModel
import org.d3if1071.idealku.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    lateinit var binding : FragmentMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,
            R.layout.fragment_main,container,false)
        viewModel=ViewModelProviders.of(this).get(MainViewModel::class.java)
//        reset()
        binding.btHitung.setOnClickListener{
            viewModel.hitung(binding.etTinggi.text.toString().toDouble(),binding.etBerat.text.toString().toDouble(),binding.rbLaki,binding.rbPerempuan,binding.tvHasilBMI,binding.tvHasilberatIdeal,binding.tvJudulBBIdeal,binding.tvJudulBMI,binding.tvJudulBMIHasil,binding.imBMI,binding.imBeratIdeal,binding.bSimpanData)


        }
        binding.tvHasilViewModel=viewModel
        binding.lifecycleOwner=this
        binding.bReset.setOnClickListener {
//            reset()
            viewModel.reset(binding.rbLaki,binding.etTinggi,binding.etBerat,binding.tvHasilBMI,binding.tvHasilberatIdeal,binding.tvJudulBBIdeal,binding.tvJudulBMI,binding.tvJudulBMIHasil,binding.imBMI,binding.imBeratIdeal,binding.bSimpanData)
        }
        if (viewModel.hasilBMI.value!=""){
            show()
        }else{
            reset()
        }
        return binding.root
    }

    fun show(){
        binding.tvJudulBBIdeal.visibility=View.VISIBLE
        binding.tvJudulBMI.visibility=View.VISIBLE
        binding.tvJudulBMIHasil.visibility=View.VISIBLE
        binding.imBMI.visibility=View.VISIBLE
        binding.tvHasilberatIdeal.visibility=View.VISIBLE
        binding.imBeratIdeal.visibility=View.VISIBLE
        binding.tvHasilBMI.visibility=View.VISIBLE
        binding.bReset.visibility=View.VISIBLE
    }
    fun reset(){
        binding.rbLaki.isChecked=true
        binding.etBerat.setText(null)
        binding.etTinggi.text=null
        binding.tvJudulBBIdeal.visibility=View.GONE
        binding.tvJudulBMI.visibility=View.GONE
        binding.tvJudulBMIHasil.visibility=View.GONE
        binding.imBMI.visibility=View.GONE
        binding.tvHasilberatIdeal.visibility=View.GONE
        binding.imBeratIdeal.visibility=View.GONE
        binding.tvHasilBMI.visibility=View.GONE
        binding.bSimpanData.visibility=View.GONE
    }

}


