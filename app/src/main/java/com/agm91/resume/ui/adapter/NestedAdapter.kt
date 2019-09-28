package com.agm91.resume.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.agm91.resume.BR
import com.agm91.resume.R
import com.agm91.resume.databinding.ItemNestedHolderBinding
import com.agm91.resume.entity.Ndo
import kotlin.properties.Delegates

class NestedAdapter(private val parentPosition: Int) : RecyclerView.Adapter<NestedAdapter.NestedHolder>() {
    private var dataList = emptyList<Ndo>()

    fun setData(dataList: List<Ndo>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNestedHolderBinding>(inflater, R.layout.item_nested_holder, parent, false)
        return NestedHolder(binding, parentPosition)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NestedHolder, position: Int) {
        holder.bind(dataList[position], getItemViewType(position), nestedElementPosition = position)
    }

    class NestedHolder(private val binding: ViewDataBinding, private val parentPosition: Int) :
            RecyclerView.ViewHolder(binding.root) {

        private var nestedElementPosition: Int by Delegates.notNull<Int>()

        fun bind(data: Ndo, nestedItemViewType: Int, nestedElementPosition: Int) {
            this.nestedElementPosition = nestedElementPosition
            when (1) {
                1 -> {
                    (binding as ItemNestedHolderBinding).setVariable(BR.itemNested, data)
                    binding.executePendingBindings()
                }
            }
        }
    }
}