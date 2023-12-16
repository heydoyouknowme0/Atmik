package com.build.atmik.mainfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.build.atmik.R
import com.build.atmik.bottoms.MFFragment
import com.build.atmik.data.Journal
import com.build.atmik.data.ViewModel
import com.build.atmik.databinding.FragmentMainBinding
import com.build.atmik.model.NewEFragment
import com.build.atmik.model.NewJFragment


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: ViewModel
    private var ids:Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        var listOfJournal: List<Journal> = emptyList()
        mViewModel.getAllJournals.observe(viewLifecycleOwner, Observer { journals ->
            listOfJournal=journals
            if(listOfJournal.size !=0) {
                 ids =listOfJournal.size-1
                 binding.bookName.text=listOfJournal.last().name
            }else{
                 ids =-1
            }
        })

        binding.newJournal.setOnClickListener {
            val dialogFragment = NewJFragment()
            dialogFragment.show(parentFragmentManager, "MyDialogFragment")
        }
        binding.newEntry.setOnClickListener {
            if(ids!=-1){
            val dialogFragment = NewEFragment(listOfJournal[ids].id)
            dialogFragment.show(parentFragmentManager, "MyDialogFragment")}
        }
        binding.leftArrow.setOnClickListener {
            if(ids>0){
                ids-=1
                binding.bookName.text=listOfJournal[ids].name
            }
        }
        binding.rightArrow.setOnClickListener {
            if(ids<listOfJournal.size-1){
                ids += 1
                binding.bookName.text=listOfJournal[ids].name
            }
        }
        binding.delete.setOnClickListener {
            if(ids!=-1){
                mViewModel.deleteByIdj(listOfJournal[ids].id)
                if(ids==0){
                    binding.bookName.text=""
                }
            }
        }
        binding.book.setOnClickListener{
            if(ids!=-1){
                val nextFragment = ListFragment(listOfJournal[ids].id)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView2, nextFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
