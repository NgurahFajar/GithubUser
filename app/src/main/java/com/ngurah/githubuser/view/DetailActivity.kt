package com.ngurah.githubuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ngurah.githubuser.R
import com.ngurah.githubuser.adapter.SectionPagerAdapter
import com.ngurah.githubuser.databinding.ActivityDetailBinding
import com.ngurah.githubuser.viewmodel.DetailViewModel


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra(EXTRA_USERNAME)

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailViewModel.setSearchUserDetail(username.toString())

        detailViewModel.user.observe(this) { user ->
            Glide.with(this)
                .load(user.avatarUrl)
                .transform(CircleCrop())
                .into(binding.imagedetail)

            binding.nama.text = user.name
            binding.loginuser.text = user.login
            binding.angkafollowers.text = user.followers.toString()
            binding.angkafollowing.text = user.following.toString()


        }

        supportActionBar?.title = username

        if (username != null) {
            setPagerAdapter(this, username)
        }

    }

    private fun setPagerAdapter(detailActivity: DetailActivity, user: String) {
        val sectionsPagerAdapter = SectionPagerAdapter(detailActivity)
        val viewPager: ViewPager2 = binding.viewpager
        viewPager.adapter = sectionsPagerAdapter
        sectionsPagerAdapter.user = user
        val tabs: TabLayout = binding.tab
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(tabTitle[position])
        }.attach()

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar3.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    companion object {
        const val EXTRA_USERNAME = "username"

        @StringRes
        private val tabTitle = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}