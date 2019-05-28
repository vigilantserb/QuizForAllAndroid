package com.stameni.com.quizforall.ui.login.mainActivity.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stameni.com.quizforall.R
import com.stameni.com.quizforall.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import javax.inject.Inject

class FavoritesFragment : BaseFragment() {

    lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var viewModelFactory: FavoritesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presentationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)

        var adapter = FavoritesAdapter()

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
        fun newInstance(): FavoritesFragment =
            FavoritesFragment()
    }
}