package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hofstedematheus.githubclientchallenge.R

class PublicRepositoriesFragment : Fragment() {

    private lateinit var dashboardViewModel: PublicRepositoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(PublicRepositoriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_public_repositories, container, false)
        return root
    }
}