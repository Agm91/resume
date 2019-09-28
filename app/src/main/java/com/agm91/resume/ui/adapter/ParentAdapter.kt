package com.agm91.resume.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.agm91.resume.BR.adapter
import com.agm91.resume.BR.dataItem
import com.agm91.resume.R
import com.agm91.resume.databinding.ItemNestedHorizontalHostBinding
import com.agm91.resume.databinding.ItemParentHolderBinding
import com.agm91.resume.entity.Holder
import com.agm91.resume.entity.Pdo
import com.agm91.resume.entity.interfa.HasType
import kotlin.properties.Delegates

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {
    private var listOfData = listOf<HasType>()

    fun setData(dataList: List<HasType>) {
        listOfData = emptyList()
        listOfData = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding: ViewDataBinding? = null

        when (viewType) {
            Holder.PARENT.type -> binding = DataBindingUtil.inflate<ItemParentHolderBinding>(inflater, R.layout.item_parent_holder, parent, false)
            Holder.NESTED.type -> binding = DataBindingUtil.inflate<ItemNestedHorizontalHostBinding>(inflater, R.layout.item_nested_horizontal_host, parent, false)
        }

        return ViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return listOfData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfData[position], getItemViewType(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return listOfData[position].type
    }

    // Inner class RecyclerView.ViewHolder

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        // Variables

        private var parentPosition: Int by Delegates.notNull()

        // Public

        fun bind(dataObject: HasType, viewType: Int, parentPosition: Int) {
            this.parentPosition = parentPosition

            when (viewType) {
                Holder.PARENT.type -> {
                    with(binding) {
                        setVariable(dataItem, dataObject as Pdo)
                        executePendingBindings()
                    }
                }
                Holder.NESTED.type -> {
                    val nestedAdapter = NestedAdapter(parentPosition).apply {
                        setData(dataList = (dataObject as NestedDataObjectWrapper).nestedDataObjectList)
                    }
                    with(binding) {
                        setVariable(adapter, nestedAdapter)
                        executePendingBindings()
                    }
                }
            }
        }
    }
}