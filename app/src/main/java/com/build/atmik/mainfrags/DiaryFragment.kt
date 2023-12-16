package com.build.atmik.mainfrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.build.atmik.R
import com.build.atmik.data.ViewModel
import kotlin.math.log


class DiaryFragment(ID:Long) : Fragment(R.layout.fragment_diary) {
    private val id=ID
    private lateinit var mViewModel: ViewModel
    private lateinit var write: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val back=view.findViewById<Button>(R.id.back)
        val undo=view.findViewById<Button>(R.id.undo)
        write=view.findViewById<EditText>(R.id.write)
        mViewModel.getData(id).observe(viewLifecycleOwner, Observer { entry ->
            write.setText(entry.data)
        })
        back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        undo.setOnClickListener {
            mViewModel.getData(id).observe(viewLifecycleOwner, Observer { entry ->
                write.setText(entry.data)
            })
        }

    }
    override fun onPause() {
        super.onPause()
        mViewModel.updateEntry(id, write.text.toString())
    }
}