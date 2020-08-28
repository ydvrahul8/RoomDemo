package com.example.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding
import com.example.roomdemo.db.Subscriber

class MyRecyclerVewAdapter(private val listener:(Subscriber)->Unit):
    RecyclerView.Adapter<MyViewHolder>() {
    private val subscriberList = ArrayList<Subscriber>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:ListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscriberList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(subscriberList!![position],listener )
    }

    fun setList(subscriber: List<Subscriber>){
        subscriberList!!.clear()
        subscriberList.addAll(subscriber)
        notifyDataSetChanged()
    }
}

class MyViewHolder(private val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(
        subscriber: Subscriber,
        listener: (Subscriber) -> Unit
    ){
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.cardView.setOnClickListener { listener(subscriber) }
    }
}