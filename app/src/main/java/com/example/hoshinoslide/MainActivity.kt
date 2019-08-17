package com.example.hoshinoslide

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer

    class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
        private val resources = listOf(
            R.drawable.hoshino1, R.drawable.hoshino2,
            R.drawable.hoshino3, R.drawable.hoshino4,
            R.drawable.hoshino5, R.drawable.hoshino6,
            R.drawable.hoshino7, R.drawable.hoshino8,
            R.drawable.hoshino9, R.drawable.hoshino10
        )
        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(resources[position])
        }

        override fun getCount(): Int {
            return resources.size
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MyAdapter(supportFragmentManager)
        val handler = Handler()
        timer(period = 5000){
            handler.post{
                pager.currentItem = (pager.currentItem + 1) % 10
            }
        }
        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true
    }

    override fun onResume() {
        super.onResume()
        player.start()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

}
