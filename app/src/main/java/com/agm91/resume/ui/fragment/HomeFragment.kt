package com.agm91.resume.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.agm91.resume.R
import com.agm91.resume.databinding.FragmentHomeBinding
import com.agm91.resume.ui.adapter.ParentAdapter
import com.agm91.resume.ui.viewHolder.HomeViewModel

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root: View = binding.root

        val homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.resumeLiveData.observe(this, Observer { resume ->
            val adapter = ParentAdapter()
            val list = resume.asList
            adapter.setData(list)
            binding.recycler.setLayoutManager(LinearLayoutManager(activity))
            binding.recycler.setAdapter(adapter)
        })

        return root
    }
}