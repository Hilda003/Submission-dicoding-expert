package com.example.submission_dicoding_expert

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.submission_dicoding_expert.databinding.ActivityMainBinding
import com.example.submission_dicoding_expert.ui.HomeFragment
import com.example.submission_dicoding_expert.ui.SettingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(HomeFragment())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    setFragment(HomeFragment())
                    true

                }
                R.id.favorite -> {
                    moveToFavorite()
                    true
                }
                R.id.setting -> {
                    setFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.layout_fragment, fragment)
            commit()
        }
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    private fun moveToFavorite() {
        val fragment = instantiateFragment()
        setFragment(fragment)
    }

    private fun instantiateFragment(): Fragment {
        return try {
            Class.forName("com.example.favorite.FavoriteFragment").newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
            Fragment()
        }

    }
}