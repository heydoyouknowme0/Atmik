 package com.build.atmik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button


 /**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomFragment : Fragment(R.layout.fragment_bottom) {
     private lateinit var buttonCreate: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCreate = view.findViewById(R.id.btn)
        val fill=AnimationUtils.loadAnimation(requireActivity(),R.anim.fill_btn)
        buttonCreate.setOnClickListener {
            buttonCreate.startAnimation(fill)
            val nextFragment = MFFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)  // Optional: Add to the back stack
            transaction.commit()
        }

    }
}