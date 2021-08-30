package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ListItemPresidentBinding
import com.example.retrofit.data.model.President

interface PresidentDataSource {
    fun getDataSetSize(): Int
    fun getPresident(index: Int): President
}

/**
 * Implement to handle onClick events for items in the president list
 */
interface PresidentClickListener {
    fun onPress(position: Int)
}

class PresidentViewHolder(
    private val binding: ListItemPresidentBinding,
    private val clickListener: PresidentClickListener
) : View.OnClickListener, RecyclerView.ViewHolder(binding.root) {
    // Memorize the position of this ViewHolder to handle click events
    private var index = -1

    // Set this ViewHolder to listen to clicks in the layout root
    init {
        this.binding.root.setOnClickListener(this)
    }

    // Bind the president data to the layout file.
    fun bind(president: President, position: Int) {
        this.index = position
        this.binding.president = president
        binding.executePendingBindings()
    }

    /**
     * Handle onclick function in the ViewHolder, instead of creating it in
     * the onBind method of the adapter again everytime the item is bound.
     */
    override fun onClick(v: View?) {
        this.clickListener.onPress(index)
    }

    companion object {
        // "Static" method which can be called to create an instance of this ItemHolder
        fun from(parent: ViewGroup, clickListener: PresidentClickListener): PresidentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemPresidentBinding.inflate(layoutInflater, parent, false)
            return PresidentViewHolder(binding, clickListener)
        }
    }
}

/**
 * Pass in a ClickListener to handle PresidentViewHolder onClick events
 */
class PresidentAdapter(
    private val dataSource: PresidentDataSource,
    private val clickListener: PresidentClickListener
) :
    RecyclerView.Adapter<PresidentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresidentViewHolder {
        return PresidentViewHolder.from(parent, clickListener)
    }

    override fun onBindViewHolder(holder: PresidentViewHolder, position: Int) {
        val president = dataSource.getPresident(position)
        holder.bind(president, position)
    }

    override fun getItemCount(): Int {
        return dataSource.getDataSetSize()
    }
}