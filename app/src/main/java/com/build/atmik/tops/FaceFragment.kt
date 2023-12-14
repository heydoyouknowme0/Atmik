package com.build.atmik.tops

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.build.atmik.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [FaceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FaceFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int = 0
    private val drawables: List<Int> = listOf(R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4, R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4)
    private var customizeProfile: TextView? = null
    private var customizeProfileLine: View? = null
    private var category: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_face, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val faceImage =view.findViewById<ImageView>(R.id.face_replace)
        val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), drawables[param1])
        customizeProfile=view.findViewById(R.id.customizeProfile)
        customizeProfileLine=view.findViewById(R.id.customizeProfileLine)
        category=view.findViewById(R.id.category)
        faceImage.setImageDrawable(drawable)
    }
    fun profile(select:Int?){
        if (select===null||select==0){
            customizeProfile?.text=""
            customizeProfileLine?.visibility=View.INVISIBLE
            category?.text=""
            return
        }
        customizeProfile?.text = getString(R.string.customize_profile)
        customizeProfileLine?.visibility=View.VISIBLE
        category?.text=getString(select)
    }
    fun updateImage(param2: Int) {
        val drawable: Drawable? = ContextCompat.getDrawable(requireContext(), drawables[param2+param1])
        view?.findViewById<ImageView>(R.id.face_replace)?.setImageDrawable(drawable)
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            FaceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}