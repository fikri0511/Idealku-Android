package org.d3if1071.idealku.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.d3if1071.idealku.MainActivity
import org.d3if1071.idealku.R
import org.d3if1071.idealku.adapter.BeratBadanAdapter
import org.d3if1071.idealku.adapter.RecyclerViewOnClick
import org.d3if1071.idealku.data.DataBeratBadan
import org.d3if1071.idealku.data.DatabaseBeratBadan
import org.d3if1071.idealku.databinding.FragmentDataBeratBadanBinding
import org.d3if1071.idealku.viewmodel.MainViewModel
import org.d3if1071.idealku.viewmodel.MainViewModelFactory
import org.d3if1071.idealku.viewmodel.ProsesDataViewmodel

/**
 * A simple [Fragment] subclass.
 */
class DataListBeratBadan : Fragment(), RecyclerViewOnClick {
    lateinit var binding : FragmentDataBeratBadanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_data_berat_badan,container,false)
        binding.lifecycleOwner=this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getActivity = requireActivity() as MainActivity
        getActivity.supportActionBar?.title = "Data Berat Badan"

        // untuk ambil diaryViewModel
        val application = requireNotNull(this.activity).application
        val dataSource = DatabaseBeratBadan.getInstance(application).dataBeratBadanDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val beratViewModel = ViewModelProvider(this, viewModelFactory).get(ProsesDataViewmodel::class.java)

        beratViewModel.listBeratBadan.observe(viewLifecycleOwner, Observer {
            val adapter = BeratBadanAdapter(it)
            val recyleview=binding.rvDataBerat
            recyleview.adapter=adapter
            recyleview.layoutManager=LinearLayoutManager(this.requireContext())

            adapter.listener=this
        })
    }

    override fun onRecyclerViewItemClicked(view: View, dataBeratBadan: DataBeratBadan) {

        when (view.id){
            R.id.list_data->{
                val bundle=Bundle()
                bundle.putLong("id",dataBeratBadan.id)
                bundle.putString("berat",dataBeratBadan.berat)
                bundle.putString("tinggi",dataBeratBadan.tinggi)
                bundle.putString("jenisKelamin",dataBeratBadan.jenisKelamin)
                bundle.putLong("tanggal",dataBeratBadan.tanggal)
                bundle.putString("hasilBMI",dataBeratBadan.hasilBMI)
                bundle.putString("hasilBeratBadanIdeal",dataBeratBadan.hasilBeratBadanIdeal)

                view.findNavController().navigate(R.id.action_dataBeratBadan_to_editDataFragment,bundle)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.deletemenu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val application = requireNotNull(this.activity).application
        val dataSource = DatabaseBeratBadan.getInstance(application).dataBeratBadanDao
        val viewModelFactory = MainViewModelFactory(dataSource, application)
        val beratViewModel = ViewModelProvider(this, viewModelFactory).get(ProsesDataViewmodel::class.java)

        return when(item.itemId){
            R.id.hapus->{
                beratViewModel.onClickHapusSemua()
                Toast.makeText(requireContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show()
                return true
            }
            else ->         return super.onOptionsItemSelected(item)
        }


    }


}
