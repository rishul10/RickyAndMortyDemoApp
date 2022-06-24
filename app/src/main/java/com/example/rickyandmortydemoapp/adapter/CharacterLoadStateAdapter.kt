//package com.example.rickyandmortydemoapp.adapter
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.paging.LoadState
//import androidx.paging.LoadStateAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.rickyandmortydemoapp.databinding.ItemLoadingStateBinding
//import com.example.rickyandmortydemoapp.utils.visible
//
//class CharacterLoadStateAdapter(
//    private val retry: () -> Unit
//) : LoadStateAdapter<CharacterLoadStateAdapter.PassengerLoadStateViewHolder>() {
//
//    inner class PassengerLoadStateViewHolder(
//        private val binding: ItemLoadingStateBinding,
//        private val retry: () -> Unit
//    ) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(loadState: LoadState) {
//            if (loadState is LoadState.Error) {
//                binding.textViewError.text = loadState.error.localizedMessage
//            }
//
//            binding.progressbar.visible(loadState is LoadState.Loading)
//            binding.buttonRetry.visible(loadState is LoadState.Error)
//            binding.textViewError.visible(loadState is LoadState.Error)
//            binding.buttonRetry.setOnClickListener {
//                retry()
//            }
//
//            binding.progressbar.visibility = View.VISIBLE
//        }
//    }
//
//    override fun onBindViewHolder(holder: PassengerLoadStateViewHolder, loadState: LoadState) {
//        holder.bind(loadState)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        loadState: LoadState
//    ) = PassengerLoadStateViewHolder(
//        ItemLoadingStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),
//        retry
//    )
//}