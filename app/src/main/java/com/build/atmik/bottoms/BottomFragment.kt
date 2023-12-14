 package com.build.atmik.bottoms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import com.build.atmik.AnimationUtility
import com.build.atmik.R


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
        buttonCreate.setOnClickListener {
            AnimationUtility.applyFillAnimation(it,requireContext())
            val nextFragment = MFFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)  // Optional: Add to the back stack
            transaction.commit()
        }

    }
}