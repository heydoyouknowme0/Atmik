package com.build.atmik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.ViewFlipper


/**
 * A simple [Fragment] subclass.
 * Use the [MFFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MFFragment : Fragment(R.layout.fragment_mf) {
    private var imageIndex = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rightButton=view.findViewById<TextView>(R.id.right_arrow)
        val leftButton=view.findViewById<TextView>(R.id.left_arrow)
        val nextButton=view.findViewById<Button>(R.id.nextButton)
        val fill=AnimationUtils.loadAnimation(requireActivity(),R.anim.fill_btn)
        val imageChange=view.findViewById<ViewFlipper>(R.id.view_flipper)
        val slideInRight = AnimationUtils.loadAnimation(context, R.anim.slide_in_right)
        val slideOutLeft = AnimationUtils.loadAnimation(context, R.anim.slide_out_left)
        val slideInLeft = AnimationUtils.loadAnimation(context, R.anim.slide_in_left)
        val slideOutRight = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
        rightButton.setOnClickListener {
            imageChange.inAnimation = slideInLeft
            imageChange.outAnimation = slideOutRight
            imageChange.showNext()

            imageIndex = (imageIndex + 1) % imageChange.childCount
        }

        leftButton.setOnClickListener {
            imageChange.inAnimation = slideInRight
            imageChange.outAnimation = slideOutLeft
            imageChange.showPrevious()
            imageIndex = (imageIndex - 1 + imageChange.childCount) % imageChange.childCount
        }
        nextButton.setOnClickListener{
            nextButton.startAnimation(fill)
            val nextFragmentTop = FaceFragment.newInstance(imageIndex)
            val nextFragment= UserIFragment()
            imageIndex=1
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.top, nextFragmentTop)
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}