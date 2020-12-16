package com.hofstedematheus.githubclientchallenge.ui.scenes.repositorypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.hofstedematheus.githubclientchallenge.R
import com.hofstedematheus.githubclientchallenge.data.constants.REPOSITORY
import com.hofstedematheus.githubclientchallenge.data.model.PublicRepository
import com.hofstedematheus.githubclientchallenge.databinding.FragmentPublicRepositoriesBinding
import com.hofstedematheus.githubclientchallenge.databinding.RepositoryPageFragmentBinding
import com.hofstedematheus.githubclientchallenge.ui.scenes.publicrepositories.PublicRepositoriesListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryPageFragment : Fragment() {

    private val viewModel: RepositoryPageViewModel by viewModel()
    private lateinit var binding: RepositoryPageFragmentBinding

    private fun setupViewModel() {
        viewModel.apply {
            repository.observe(
                    viewLifecycleOwner,
                    { repository ->
                        binding.apply {
                            repositoryNameTXT.text = repository.name
                            repositoryDescriptionTXT.text = repository.description
                            context?.let { context ->
                                Glide
                                    .with(context)
                                    .load(repository.owner.avatarUrl)
                                    .centerCrop()
                                    .placeholder(R.drawable.profile_blank)
                                    .into(profileImageIMG)
                            }
                            ownerNameTXT.text = repository.owner.userName
                        }

                    }
            )
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val repository = arguments?.getSerializable(REPOSITORY) as PublicRepository
        viewModel.updateRepository(repository)
        setupViewModel()

        binding = RepositoryPageFragmentBinding.inflate(inflater)
        return binding.root
    }

}