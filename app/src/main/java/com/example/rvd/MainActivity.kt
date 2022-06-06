package com.example.rvd
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rvd.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().setCustomAnimations(
            android.R.animator.fade_in, android.R.animator.fade_out
        ).replace(R.id.defaultFragment, MyFragment()).commit()
    }

}