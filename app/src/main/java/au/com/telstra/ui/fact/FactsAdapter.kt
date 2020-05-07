package au.com.telstra.ui.fact

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FactsAdapter : ListAdapter<FactView.DataModel, FactsAdapter.ViewHolder>(FactDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemCampaign = FactView(parent.context)
        return ViewHolder(itemCampaign)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(campaign: FactView.DataModel) {
            (itemView as FactView).bindData(campaign)
        }
    }
}

class FactDiffCallback : DiffUtil.ItemCallback<FactView.DataModel>() {
    override fun areItemsTheSame(oldItem: FactView.DataModel, newItem: FactView.DataModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: FactView.DataModel, newItem: FactView.DataModel): Boolean {
        return oldItem == newItem
    }
}
