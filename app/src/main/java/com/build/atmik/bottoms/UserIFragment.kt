package com.build.atmik.bottoms

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.build.atmik.AnimationUtility
import com.build.atmik.R
import com.build.atmik.tops.FaceFragment
import java.util.Calendar
class UserIFragment : Fragment(R.layout.fragment_user_i) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameEdit = view.findViewById<EditText>(R.id.nameEdit)
        val nicknameEdit = view.findViewById<EditText>(R.id.nicknameEdit)
        val numberEdit = view.findViewById<EditText>(R.id.numberEdit)
        val dateEdt = view.findViewById<EditText>(R.id.birthEdit)
        val nxtBtn= view.findViewById<Button>(R.id.nextButton)
        dateEdt.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        if(sharedPreferences.contains("name")){
            nameEdit.setText(sharedPreferences.getString("name",""))
            nicknameEdit.setText(sharedPreferences.getString("nickname",""))
            dateEdt.setText(sharedPreferences.getString("birthDate",""))
            nameEdit.setText(sharedPreferences.getString("phoneNumber",""))
        }
        nxtBtn.setOnClickListener{
            val name = nameEdit.text.toString().trim()
            val nickname = nicknameEdit.text.toString().trim()
            val birthDate = dateEdt.text.toString().trim()
            val phoneNumber = numberEdit.text.toString().trim()

            AnimationUtility.applyFillAnimation(it,requireContext())
            if(!validateInputs(name,nickname,birthDate,phoneNumber)){
                return@setOnClickListener
            }else{
                setName(sharedPreferences,name,nickname,birthDate,phoneNumber)
            }

            val nextFragment = SelectFragment.newInstance(sharedPreferences.getInt("gender",0))
            val faceFragment= parentFragmentManager.findFragmentByTag("faceFragment") as FaceFragment?
            faceFragment?.profile(R.string.select_costume)
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.bottom, nextFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
    private fun setName(sharedPreferences: SharedPreferences, name: String, nickname: String, birthDate: String, phoneNumber: String) {

        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("nickname", nickname)
        editor.putString("birthDate", birthDate)
        editor.putString("phoneNumber", phoneNumber)
        editor.apply()
    }
    private fun validateInputs(name:String,nickname:String,birthDate:String,phoneNumber:String): Boolean {
        var isValid = true

        if (name.isEmpty()) {
            showToast(getString(R.string.enter_name_error))
            isValid = false
        }

        else if (nickname.isEmpty()) {
            showToast(getString(R.string.enter_nickname_error))
            isValid = false
        }

        else if (birthDate.isEmpty()) {
            showToast(getString(R.string.enter_birthdate_error))
            isValid = false
        }

        else if (phoneNumber.isEmpty()) {
            showToast(getString(R.string.enter_phone_error))
            isValid = false
        }
        return isValid
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}