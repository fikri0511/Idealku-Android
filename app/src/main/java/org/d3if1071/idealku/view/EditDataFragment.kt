package org.d3if1071.idealku.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.d3if1071.idealku.MainActivity
import org.d3if1071.idealku.R
import org.d3if1071.idealku.convertWaktu
import org.d3if1071.idealku.data.DataBeratBadan
import org.d3if1071.idealku.data.DatabaseBeratBadan
import org.d3if1071.idealku.databinding.FragmentEditDataBinding
import org.d3if1071.idealku.viewmodel.MainViewModel
import org.d3if1071.idealku.viewmodel.MainViewModelFactory
import org.d3if1071.idealku.viewmodel.ProsesDataViewmodel

/**
 * A simple [Fragment] subclass.
 */
class EditDataFragment : Fragment() {
    lateinit var binding :FragmentEditDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val getActivity = requireActivity() as MainActivity
        getActivity.supportActionBar?.title = "Edit Data"
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_edit_data,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = DatabaseBeratBadan.getInstance(application).dataBeratBadanDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProsesDataViewmodel::class.java)



        //Hapus Data
        binding.btHapus.setOnClickListener {
            val id=requireArguments().getLong("id")
            viewModel.onClickDelete(id)

            requireView().findNavController().popBackStack()
            Toast.makeText(requireContext(),"Sukses Mengahapus Data", Toast.LENGTH_SHORT).show()
        }
        var hitungViewModel= ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModelHitung=hitungViewModel
        binding.lifecycleOwner = this


//        Edit Data
        binding.btHitung.setOnClickListener{
            hitungViewModel.hitung(binding.etTinggi.text.toString().toDouble(),binding.etBerat.text.toString().toDouble(),binding.rbLaki,binding.rbPerempuan,binding.tvHasilBMI,binding.tvHasilberatIdeal,binding.tvJudulBBIdeal,binding.tvJudulBMI,binding.tvJudulBMIHasil,binding.imBMI,binding.imBeratIdeal,binding.bReset)
        }

        //Simpan Edit Data
        binding.bReset.setOnClickListener {
            if (checkInputan(requireArguments().getLong("id"),viewModel)){
                requireView().findNavController().popBackStack()
                Toast.makeText(requireContext(), "Sukses MEngubah Data", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(),"Gagal Mengubah Data", Toast.LENGTH_SHORT).show()
            }
        }





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {

            val berat = requireArguments().getString("berat")
            val tinggi = requireArguments().getString("tinggi")
            val jenisKelamin = requireArguments().getString("jenisKelamin")
            val hasilBMI = requireArguments().getString("hasilBMI")
            val hasilBeratBadanIdeal = requireArguments().getString("hasilBeratBadanIdeal")
            val tanggal=requireArguments().getLong("tanggal")

            binding.tvHasilBMILama.text = hasilBMI
            binding.tvHasilberatIdealLama.text = hasilBeratBadanIdeal
            binding.tvDate.text= convertWaktu(tanggal)

            binding.etBerat.setText(berat)
            binding.etTinggi.setText(tinggi)
            if (jenisKelamin=="Laki-Laki"){
                binding.rbLaki.isChecked=true
            }else if(jenisKelamin=="Perempuan"){
                binding.rbPerempuan.isChecked=true
            }

        }else{
            Toast.makeText(requireContext(),"Tak Ada Data ", Toast.LENGTH_SHORT).show()

        }
        }

    //check input
    private fun checkInputan(id:Long, prosesDataViewmodel: ProsesDataViewmodel):Boolean{
        return when{
            binding.etBerat.text?.trim()?.isEmpty()!!&&binding.etTinggi.text?.trim()?.isEmpty()!!->false
            else->{
                prosesUpdate(id,prosesDataViewmodel)
                true
            }
        }
    }


    private fun prosesUpdate(id:Long,prosesDataViewmodel: ProsesDataViewmodel){
        var jenisKelamin:String=""
        if (binding.rbLaki.isChecked){
            jenisKelamin="Laki-Laki"
        }else if (binding.rbPerempuan.isChecked){
            jenisKelamin="Perempuan"
        }
        val dataBeratBadan=DataBeratBadan(id,binding.etBerat.text.toString(),binding.etTinggi.text.toString(),jenisKelamin,
            binding.tvHasilberatIdeal.text.toString(),binding.tvHasilBMI.text.toString())

        prosesDataViewmodel.onClickUpdate(dataBeratBadan)

    }




}
