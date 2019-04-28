package com.brainbowfx.android.freenotes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.adapters.NotesListAdapter
import com.brainbowfx.android.freenotes.presentation.utils.NotesItemAnimator
import com.brainbowfx.android.freenotes.presentation.view.contract.NotesListView
import javax.inject.Inject

open class BaseListFragment : MvpAppCompatFragment(), NotesListView {

    @Inject
    lateinit var notesListAdapter: NotesListAdapter

    lateinit var rvNotesList: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_notes_list, container, false)
        rvNotesList = root.findViewById(R.id.rvNotesList)

        rvNotesList.itemAnimator = NotesItemAnimator()
        rvNotesList.adapter = notesListAdapter

        return root
    }

    override fun setData(data: MutableList<Note>) {
        notesListAdapter.setData(data)
    }

    override fun removeNoteAt(position: Int) {
        notesListAdapter.removeAt(position)
    }
}