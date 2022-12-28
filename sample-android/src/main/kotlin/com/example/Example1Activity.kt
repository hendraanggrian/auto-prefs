package com.example

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.hendraanggrian.auto.prefs.BindPreference
import com.hendraanggrian.auto.prefs.PreferencesSaver
import com.hendraanggrian.auto.prefs.android.bindPreferences
import kotlinx.android.synthetic.main.activity_example1.*

class Example1Activity : AppCompatActivity(), View.OnClickListener {
    @JvmField @BindPreference("name") var name: String? = null
    @JvmField @BindPreference("married") var married: Boolean = false
    @JvmField @BindPreference("int") var mInt: Int = 0
    @JvmField @BindPreference("long") var mLong: Long = 0L
    @JvmField @BindPreference("float") var mFloat: Float = 0f

    private lateinit var saver: PreferencesSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example1)
        setSupportActionBar(toolbar)
        saver = bindPreferences()

        nameEditText.setText(name)
        marriedEditText.setText(married.toString())
        intEditText.setText(mInt.toString())
        longEditText.setText(mLong.toString())
        floatEditText.setText(mFloat.toString())
    }

    override fun onClick(v: View) {
        name = nameEditText.text.toString()
        married = marriedEditText.text.toString().toBoolean()
        mInt = intEditText.text.toString().toInt()
        mLong = longEditText.text.toString().toLong()
        mFloat = floatEditText.text.toString().toFloat()
        saver.save()
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_example1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.reset -> PreferenceManager.getDefaultSharedPreferences(this).edit { clear() }
        }
        return true
    }
}
