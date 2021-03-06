package com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.core.extensions.addDebouncedTextListener
import com.hofstedematheus.githubclientchallenge.core.extensions.isVisibleIf
import com.hofstedematheus.githubclientchallenge.data.constants.REPOSITORY
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
        setupListeners()
        setupViewModel()
        getPublicRepositories()

        return binding.root
    }

    private fun initUi() {
        binding.apply {
            repositoriesRV.layoutManager = LinearLayoutManager(activity?.applicationContext)
            repositoriesRV.adapter = viewModel.publicRepositories.value?.let { list ->

                PublicRepositoriesListAdapter(list) { repository ->
                    val bundle = bundleOf(
                            REPOSITORY to repository
                    )
                    findNavController().navigate(R.id.action_navigation_public_repositories_to_navigation_repository_page, bundle)
                }
            }
            repositoriesRV.setHasFixedSize(true)
        }
    }

    private fun setupListeners() {
        binding.swipeToRefresh.apply {
            setOnRefreshListener {
                getPublicRepositories()
                this.isRefreshing = false
            }
        }
        binding.searchEditText.addDebouncedTextListener(1000L, lifecycle) { query ->
            if (query.isNotBlank()) viewModel.searchPublicRepositoryByName(query)
            else getPublicRepositories()
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            publicRepositories.observe(
                viewLifecycleOwner,
                { list ->
                    binding.repositoriesRV.apply {
                        adapter = PublicRepositoriesListAdapter(list) { repository ->
                            val bundle = bundleOf(
                                    REPOSITORY to repository
                            )
                            findNavController().navigate(R.id.action_navigation_public_repositories_to_navigation_repository_page, bundle)
                        }
                    }
                }
            )

            error.observe(
                viewLifecycleOwner,
                { error ->
                    binding.errorTitle.text = error
                    binding.errorTitle isVisibleIf error.isNotBlank()
                    binding.repositoriesRV isVisibleIf error.isBlank()
                }
            )

            isFetchingData.observe(
                viewLifecycleOwner,
                { isFetchingData ->
                    binding.loadingProgress isVisibleIf isFetchingData
                    binding.repositoriesRV isVisibleIf !isFetchingData
                }
            )
        }

    }

    private fun getPublicRepositories() {
        viewModel.getPublicRepositoriesList()
    }
}