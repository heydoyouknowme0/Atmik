package com.build.atmik.bottoms
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.build.atmik.R
import com.build.atmik.tops.FaceFragment


private const val ARG_mf = "mf"
 


class SelectFragment : Fragment(R.layout.fragment_select) {
    private var mf: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mf = it.getInt(ARG_mf)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridView: GridView = view.findViewById(R.id.costumeGrid)
        val nextButton= view.findViewById<Button>(R.id.next)
        val prevButton= view.findViewById<Button>(R.id.back)
        val faceFragment= parentFragmentManager.findFragmentByTag("faceFragment") as FaceFragment?
        var preferredPosition=1
        gridView.adapter = object : BaseAdapter() {


            override fun getCount(): Int = 4

            override fun getItem(position: Int): Any? = null

            override fun getItemId(position: Int): Long = 0

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

                val imageIds = if(mf==1){
                    intArrayOf(R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4)
                }else {
                    intArrayOf(
                        R.drawable.f1,
                        R.drawable.f2,
                        R.drawable.f3,
                        R.drawable.f4
                    )
                }
                val imageView = convertView as? ImageView ?: LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false) as ImageView
                imageView.setImageResource(imageIds[position])
                imageView.setOnClickListener {
                    faceFragment?.updateImage(position)
                    preferredPosition=position
                }
                return imageView
            }

        }
        nextButton.setOnClickListener{
            setIcon(requireContext(),preferredPosition)
        }
        prevButton.setOnClickListener{
            val faceFragment= parentFragmentManager.findFragmentByTag("faceFragment") as FaceFragment?
            faceFragment?.profile(0)
            parentFragmentManager.popBackStack()
        }
    }
    fun setIcon(context: Context,option:Int){
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("costume", option)
        editor.apply()
    }
    companion object {
        @JvmStatic
        fun newInstance(mf: Int) =
            SelectFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_mf, mf)
                }
            }
    }
}