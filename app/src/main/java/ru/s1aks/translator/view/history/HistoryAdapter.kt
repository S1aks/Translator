package ru.s1aks.translator.view.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.s1aks.translator.databinding.ActivityHistoryRecyclerviewItemBinding
import ru.s1aks.translator.model.data.DataModel

class HistoryAdapter(
    private var onListItemClickListener: OnListItemClickListener,
) : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private lateinit var binding: ActivityHistoryRecyclerviewItemBinding
    private var data: List<DataModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        binding = ActivityHistoryRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(
        private val itemBinding: ActivityHistoryRecyclerviewItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemBinding.headerHistoryTextviewRecyclerItem.text = data.text
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}
