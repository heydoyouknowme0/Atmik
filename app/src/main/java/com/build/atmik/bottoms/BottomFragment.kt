 package com.build.atmik.bottoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.build.atmik.R



class BottomFragment : Fragment(R.layout.fragment_bottom) {
     private lateinit var buttonCreate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCreate = view.findViewById(R.id.btn)
        buttonCreate.setOnClickListener {
            val nextFragment = MFFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)  // Optional: Add to the back stack
            transaction.commit()
        }

    }
}