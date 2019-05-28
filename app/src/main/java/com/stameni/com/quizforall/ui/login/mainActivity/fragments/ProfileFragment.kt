package com.stameni.com.quizforall.ui.login.mainActivity.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.stameni.com.quizforall.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initToolbar()
    }

    companion object {
        fun newInstance(): ProfileFragment =
            ProfileFragment()
    }

    private fun initComponent() {
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val minHeight = ViewCompat.getMinimumHeight(collapsing_toolbar) * 2
            val scale = (minHeight + verticalOffset).toFloat() / minHeight
            image.scaleX = if (scale >= 0) scale else 0F
            image.scaleY = if (scale >= 0) scale else 0F
        })
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_menu)
        toolbar.navigationIcon!!.setColorFilter(resources.getColor(R.color.grey_3), PorterDuff.Mode.SRC_ATOP)
        toolbar.title = "Profile"
    }
}