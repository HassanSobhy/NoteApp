package com.example.noteapp.ui.home


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource)
        val homeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel = homeViewModel
        binding.setLifecycleOwner(this)

        // Add an Observer on the state variable for Navigating when Add Note button is pressed.
        homeViewModel.navigateToAddNoteFragment.observe(this, Observer {
            if (it == true) // Observed state is true.
            {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionMainFragmentToAddNoteFragment())
                homeViewModel.doneNavigating()
            }
        })

        val adapter = NoteAdapter()
        binding.noteList.adapter = adapter
        homeViewModel.notes.observe(this, Observer {
            it?.let {
                adapter.data = it
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.clear_all_action -> deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
