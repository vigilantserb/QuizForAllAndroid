package com.stameni.com.quizforall.ui.login.mainActivity.fragments.explore

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
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject

class ExploreFragment : BaseFragment() {

    lateinit var viewModel: ExploreViewModel

    @Inject
    lateinit var viewModelFactory: ExploreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExploreViewModel::class.java)

        var adapter = ExploreQuizzesAdapter()

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
        fun newInstance(): ExploreFragment =
            ExploreFragment()
    }
}