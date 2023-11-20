package com.example.wiczenie2android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wiczenie2android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val checkable= booleanArrayOf(false,false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val theme1btn : Button = findViewById(R.id.theme1) as Button
        val theme2btn: Button  = binding.theme2
        val theme3btn: Button  = binding.theme3


        registerForContextMenu(theme1btn)
        registerForContextMenu(theme2btn)

        val actRight: Button = binding.actright

        actRight.setOnClickListener {view: View ->
            val intent = Intent(this,ActivityRight::class.java)
            startActivity(intent)
            //startActForResult.launch(intent)
        }

    }

    private fun setPrefsTheme(themeNum: Int) {
        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val editor = data.edit()
        editor.putInt("theme_num",themeNum)
        editor.apply()
    }


    private fun setPrefsFont(fontNum: Int) {
        val data : SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val editor = data.edit()
        editor.putInt("font_num", fontNum)
        editor.apply()
    }

    private fun applyTheme() {
        val data = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        var themeNum = data.getInt("theme_num",0)
        var fontNum = data.getInt("font_num",0)
        when(themeNum) {
            1-> setTheme(R.style.AppTheme1)
            2-> setTheme(R.style.AppTheme2)
            3-> setTheme(R.style.AppTheme3)
            else-> setTheme(R.style.Theme_ĆWICZENIE2Android)
        }
        when(fontNum) {
            1-> setTheme(R.style.LargeText)
            2-> setTheme(R.style.SmallText)
            else-> setTheme(R.style.NormalText)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_comeback ->{
                setPrefsTheme(0)
                recreate()
                true
            }
            R.id.action_option1 -> {
                setPrefsTheme(1)
                recreate()
                return true;
            }
            R.id.action_option2 -> {
                setPrefsTheme(2)
                recreate()
                return true;
            }
            R.id.action_option3 -> {
                setPrefsTheme(3)
                recreate()
                return true;
            }
            R.id.ltext -> {
                setPrefsFont(1)
                recreate()
                return true;
            }
            R.id.stext -> {
                setPrefsFont(2)
                recreate()
                return true;
            }
            R.id.reset -> {
                // Resetowanie ustawień czcionki do domyślnych wartości
                setPrefsFont(0)
                recreate()
                true
            }
            else -> {

                super.onOptionsItemSelected(item)
            }

        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if(v.id == R.id.theme1) {
            menuInflater.inflate(R.menu.cm_fontsize,menu)
            menu.setHeaderTitle("Font Size Menu")

        }
        if(v.id == R.id.theme2) {
            menuInflater.inflate(R.menu.cm_fonttype,menu)
            menu.setHeaderTitle("Font Type Menu")

            val mi1 = menu.getItem(0)
            mi1.isChecked=checkable.get(0)

            val mi2 = menu.getItem(1)
            mi2.isChecked=checkable.get(1)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val theme1btn = binding.theme1
        val theme2btn = binding.theme2
        val theme3btn = binding.theme3
        return when (item.itemId)
        {
            R.id.pos1s -> {
                theme1btn.textSize = 20F
                true
            }
            R.id.pos2s -> {
                theme1btn.textSize = 22F
                true
            }
            R.id.pos3s -> {
                theme1btn.textSize = 24F
                true
            }
            R.id.pos1f -> {
                if (item.isChecked && checkable.get(1)) {
                    checkable.set(0,false)
                    item.isChecked=false
                    theme2btn.setTypeface(null, Typeface.ITALIC);
                    true
                } else if (item.isChecked) {
                    checkable.set(0,false)
                    item.isChecked=false
                    theme2btn.setTypeface(null, Typeface.NORMAL);
                    true
                }
                else if (!item.isChecked && checkable.get(1)) {
                    checkable.set(0,true)
                    item.isChecked = true
                    theme2btn.setTypeface(null, Typeface.BOLD_ITALIC);
                    true
                }
                else {
                    checkable.set(0,true)
                    item.isChecked = true
                    theme2btn.setTypeface(null, Typeface.BOLD);
                    true
                }
            }
            R.id.pos2f -> {
                if (item.isChecked && checkable.get(0)) {
                    checkable.set(1,false)
                    item.isChecked=false
                    theme2btn.setTypeface(null, Typeface.BOLD);
                    true
                } else if (item.isChecked) {
                    checkable.set(1,false)
                    item.isChecked = false
                    theme2btn.setTypeface(null, Typeface.NORMAL);
                    true
                }
                else if (!item.isChecked && checkable.get(0)) {
                    checkable.set(1,true)
                    item.isChecked = true
                    theme2btn.setTypeface(null, Typeface.BOLD_ITALIC);
                    true
                }
                else {
                    checkable.set(1,true)
                    item.isChecked = true
                    theme2btn.setTypeface(null, Typeface.ITALIC);
                    true
                }
            }
            else ->
                super.onOptionsItemSelected(item)
        }
    }


}