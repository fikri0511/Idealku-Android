package org.d3if1071.idealku.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.d3if1071.idealku.MainActivity
import org.d3if1071.idealku.R
import org.d3if1071.idealku.data.DatabaseBeratBadan
import org.d3if1071.idealku.viewmodel.MainViewModel
import org.d3if1071.idealku.databinding.FragmentMainBinding
import org.d3if1071.idealku.viewmodel.MainViewModelFactory
import org.d3if1071.idealku.viewmodel.ProsesDataViewmodel

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val getActivity = requireActivity() as MainActivity
        getActivity.supportActionBar?.title = "Cek Berat Badan Ideal"

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
//        reset()
        binding.btHitung.setOnClickListener {
            viewModel.hitung(
                binding.etTinggi.text.toString().toDouble(),
                binding.etBerat.text.toString().toDouble(),
                binding.rbLaki,
                binding.rbPerempuan,
                binding.tvHasilBMI,
                binding.tvHasilberatIdeal,
                binding.tvJudulBBIdeal,
                binding.tvJudulBMI,
                binding.tvJudulBMIHasil,
                binding.imBMI,
                binding.imBeratIdeal,
                binding.bSimpanData
            )
        }
        binding.tvHasilViewModel = viewModel
        binding.lifecycleOwner = this
        binding.bReset.setOnClickListener {
            //            reset()
            viewModel.reset(
                binding.rbLaki,
                binding.etTinggi,
                binding.etBerat,
                binding.tvHasilBMI,
                binding.tvHasilberatIdeal,
                binding.tvJudulBBIdeal,
                binding.tvJudulBMI,
                binding.tvJudulBMIHasil,
                binding.imBMI,
                binding.imBeratIdeal,
                binding.bSimpanData
            )
        }
        if (viewModel.hasilBMI.value != "") {
            show()
        } else {
            reset()
        }


        // memanggil diaryViewModel
        val application = requireNotNull(this.activity).application
        val dataSource = DatabaseBeratBadan.getInstance(application).dataBeratBadanDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val beratViewModel =
            ViewModelProvider(this, viewModelFactory).get(ProsesDataViewmodel::class.java)


        //penyimpanan data
        binding.bSimpanData.setOnClickListener {
            if (checkInputan(beratViewModel)) {
                requireView().findNavController()
                    .navigate(R.id.action_mainFragment2_to_dataBeratBadan)
                Toast.makeText(requireContext(), "Berhasil Masukan Data", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Gagal Masukan Data", Toast.LENGTH_SHORT).show()
            }
        }
        //Radio Button

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menmain, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.lihatData -> {
                requireView().findNavController()
                    .navigate(R.id.action_mainFragment2_to_dataBeratBadan)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    //checkinput
    private fun checkInputan(mainViewModel: ProsesDataViewmodel):Boolean{
        return when{
            binding.etTinggi.text?.trim()?.isEmpty()!!&&binding.etBerat.text?.trim()?.isEmpty()!!->false
            else->{
                insert(mainViewModel)
                true
            }
        }
    }

    private fun insert(mainViewModel: ProsesDataViewmodel){
        var jenisKelamin:String=""
       if (binding.rbLaki.isChecked){
           jenisKelamin="Laki-Laki"
       }else if (binding.rbPerempuan.isChecked){
           jenisKelamin="Perempuan"
       }

        mainViewModel.onClickSimpanData(binding.etBerat.text.toString(),binding.etTinggi.text.toString(),jenisKelamin,
            binding.tvHasilberatIdeal.text.toString(),binding.tvHasilBMI.text.toString()
            )
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


