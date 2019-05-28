package com.stameni.com.quizforall.ui.login.mainActivity.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        var adapter = QuizAdapter()

        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.setHasFixedSize(true)

        viewModel.itemPagedList.observe(this, Observer {
            if (it != null) {
                adapter.submitList(it)
            }
        })

        nested_scroll_view.setOnRefreshListener {
            viewModel.itemPagedList.value!!.dataSource.invalidate()
            nested_scroll_view.isRefreshing = false
        }

        recycler_view.adapter = adapter

    }

    companion object {
        fun newInstance(): HomeFragment =
            HomeFragment()
    }
}
