package com.example.noteapp.ui.addnote


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.ui.home.HomeViewModel
import com.example.noteapp.ui.home.HomeViewModelFactory


class AddNoteFragment : Fragment() {


    lateinit var addNoteViewModel: AddNoteViewModel
    lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentAddNoteBinding>(
            inflater,
            R.layout.fragment_add_note,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = AddNoteViewModelFactory(dataSource)
        addNoteViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddNoteViewModel::class.java)

        addNoteViewModel.navigateToHomeFragment.observe(this, Observer {
            if (it==true)
            {
                this.findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToMainFragment())
                addNoteViewModel.doneNavigating()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save_action -> {
                if (validateData())
                {
                    addNoteViewModel.onSaveClicked(
                        binding.titleText.text.toString(),
                        binding.descText.text.toString()
                    )
                } else
                {
                    Toast.makeText(context!!,"Can't leave title or description empty" , Toast.LENGTH_SHORT).show()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun validateData() = !TextUtils.isEmpty(binding.titleText.text.toString()) && !TextUtils.isEmpty(binding.descText.text.toString())
}
