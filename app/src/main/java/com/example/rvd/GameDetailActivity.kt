package com.example.rvd

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rvd.fragment.*
import kotlinx.android.synthetic.main.activity_detail_game.*

class GameDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_game)
        setSupportActionBar(gameToolBar)
        supportActionBar?.apply {
            title = "Game Detail"
            // show back button on toolbar
            // on back button press, it will navigate to parent activity
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.back_button)
        }
        val name = intent.getStringExtra(NAME)
        val desc = intent.getStringExtra(DESCRIPTION)
        val image = intent.getStringExtra(IMAGE)
        val username = intent.getStringExtra(USER_NAME)
        val imageProfile = intent.getStringExtra(USER_IMAGE)

        when(name == "") {
            true -> detailNameView.text = "No name"
            false -> detailNameView.text = intent.getStringExtra(NAME)
        }
        when(desc == "") {
            true -> detail_descriptionView.text = "No description"
            false -> detail_descriptionView.text = intent.getStringExtra(DESCRIPTION)
        }
        Glide.with(this).load(image).into(detailImageView)
        Glide.with(this).load(imageProfile).into(userImage)
        userImage.setOnClickListener {
            showDefaultDialog(username!!)
        }
    }
    private fun showDefaultDialog( name: String) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Creator")
            setMessage(name)
        }.create().show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }

}