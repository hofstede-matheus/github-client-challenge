package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.core.extensions.isVisibleIf
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.databinding.FragmentPublicRepositoriesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PublicRepositoriesFragment : Fragment() {

    private val viewModel: PublicRepositoriesViewModel by viewModel()
    private lateinit var binding: FragmentPublicRepositoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPublicRepositoriesBinding.inflate(inflater)

        initUi()
        setupViewModel()
        getPublicRepositories()

        return binding.root
    }

    private fun initUi() {
        binding.apply {
            repositoriesRV.layoutManager = LinearLayoutManager(activity?.applicationContext)
            repositoriesRV.adapter = viewModel.publicRepositories.value?.let { list ->
                PublicRepositoriesListAdapter(list)
            }
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            publicRepositories.observe(
                viewLifecycleOwner,
                { list ->
                    binding.repositoriesRV.apply {
                        adapter = PublicRepositoriesListAdapter(list)
                    }
                }
            )

            error.observe(
                viewLifecycleOwner,
                { error ->
                    binding.errorTitle isVisibleIf error.isNotBlank()
                    binding.repositoriesRV isVisibleIf error.isNullOrEmpty()
                }
            )
        }

    }

    private fun getPublicRepositories() {
        viewModel.getPublicRepositoriesList()
    }
}