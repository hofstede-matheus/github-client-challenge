package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.core.extensions.inflate
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import kotlinx.android.synthetic.main.item_repositories_list.view.*

class PublicRepositoriesListAdapter (private val list: List<PublicRepository>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : RecyclerView.ViewHolder(parent.inflate(R.layout.item_repositories_list)) {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        list[position].let { data ->
            with(holder.itemView) {
                cardUserTitle?.text = data.name
                cardUserDescription?.text = data.description
                cardUserName?.text = data.owner.userName
                profile_image?.let { imageProfile ->
                    Glide
                        .with(context)
                        .load(data.owner.avatarUrl)
                        .centerCrop()
                        .placeholder(R.drawable.profile_blank)
                        .into(imageProfile)
                }
            }
        }
    }


}