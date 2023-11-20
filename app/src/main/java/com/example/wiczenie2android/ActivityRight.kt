package com.example.wiczenie2android

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wiczenie2android.databinding.ActivityRightBinding
import java.util.Calendar

class ActivityRight : AppCompatActivity() {
    lateinit var binding : ActivityRightBinding
    private var myAM: ActionMode? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // applyTheme()
        binding = ActivityRightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val toolbar = binding.toolbar2
        //setSupportActionBar(toolbar)


        val myAMCallback: ActionMode.Callback = object: ActionMode.Callback{
            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                val inflater = mode.menuInflater
                inflater.inflate(R.menu.cam_view,menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                var color_tv:TextView = binding.textcolor

                return when (item.itemId) {
                    R.id.item1c -> {
                        color_tv.setBackgroundColor(Color.RED);
                        mode.finish()
                        true
                    }
                    R.id.item2c -> {
                        color_tv.setBackgroundColor(Color.GREEN);
                        mode.finish()
                        true
                    }
                    R.id.item3c -> {
                        color_tv.setBackgroundColor(Color.BLUE);
                        mode.finish()
                        true
                    }
                    else -> {
                        mode.finish()
                        false
                    }
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                myAM=null
            }
        }


        val tv4 = findViewById(R.id.textView4) as TextView
        tv4!!.setOnLongClickListener(View.OnLongClickListener {view ->
            if(myAM!= null) {
                return@OnLongClickListener false
            }
            myAM = startActionMode(myAMCallback)
            view.isSelected = true
            true

        })

        var tvDate: TextView = binding.tvDate
        //Set a click listener on the "Select Date" button
                tvDate.setOnClickListener {
                    // Show the DatePicker dialog
                    showDatePicker()
                }


        val btnback: Button = binding.btnback
        btnback.setOnClickListener { view: View ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@ActivityRight)
            val listItems = arrayOf("option 1", "option 2", "option 3")
            val checkedItems = BooleanArray(listItems.size)
            Log.d("Dialog", "Items: ${listItems.joinToString()}")

            builder
                .setTitle("Go Back Dialog")
                .setPositiveButton("Accept") { _, _ ->
                    // Handle positive button click
                    showSelectedOptions(checkedItems)
                    onBackPressed()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }

            builder.setMultiChoiceItems(listItems, checkedItems) { dialog, which, isChecked ->
            checkedItems[which] = isChecked
        }
            val dialog = builder.create()
            dialog.show()

        }

    }

    fun showSelectedOptions(checkedItems: BooleanArray) {
        val selectedOptions = mutableListOf<String>()
        val listItems = arrayOf("option 1", "option 2", "option 3")

        for (i in checkedItems.indices) {
            if (checkedItems[i]) {
                selectedOptions.add(listItems[i])
            }
        }

        if (selectedOptions.isNotEmpty()) {
            val message = "Selected Options: ${selectedOptions.joinToString()}"
            Toast.makeText(this@ActivityRight, message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        var tvDate: TextView = binding.tvDate
        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this, {DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                binding.tvDate.text = (dayOfMonth.toString()+ "-" +(monthOfYear+1)+ "-"+ year)

            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // Show the DatePicker dialog
        datePickerDialog.show()
    }
    // options menu form mainactivity
//
//    private fun setPrefs(themeNum: Int) {
//        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
//        val editor = data.edit()
//        editor.putInt("theme_num",themeNum)
//        editor.apply()
//    }
    private fun applyTheme() {
        val data = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        var themeNum = data.getInt("theme_num",0)
        when(themeNum) {
            1-> setTheme(R.style.AppTheme1)
            2-> setTheme(R.style.AppTheme2)
            3-> setTheme(R.style.AppTheme3)
            else-> setTheme(R.style.Theme_ĆWICZENIE2Android)
        }
    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_comeback ->{
//                setPrefs(0)
//                recreate()
//                true
//            }
//            R.id.action_option1 -> {
//                setPrefs(1)
//                recreate()
//                return true;
//            }
//            R.id.action_option2 -> {
//                setPrefs(2)
//                recreate()
//                return true;
//            }
//            R.id.action_option3 -> {
//                setPrefs(3)
//                recreate()
//                return true;
//            }
//            else -> {
//
//                super.onOptionsItemSelected(item)
//            }
//
//        }
//    }


}


