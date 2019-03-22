package com.brainbowfx.android.simplenotes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brainbowfx.android.simplenotes.R
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.presentation.App
import com.brainbowfx.android.simplenotes.presentation.adapters.NotesListAdapter
import com.brainbowfx.android.simplenotes.presentation.presenter.NotesListPresenter
import com.brainbowfx.android.simplenotes.presentation.utils.NotesItemAnimator
import com.brainbowfx.android.simplenotes.presentation.utils.NotesSimpleCallback
import com.brainbowfx.android.simplenotes.presentation.view.contract.NotesListView
import javax.inject.Inject

class NotesListFragment : MvpAppCompatFragment(), NotesListView {

    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    @Inject
    lateinit var notesListAdapter: NotesListAdapter

    lateinit var rvNotesList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.Instance.activitySubcomponent?.inject(this)
        App.Instance.activityPerInstanceSubcomponent?.inject(presenter)

        rvNotesList = view.findViewById(R.id.rvNotesList)

        notesListAdapter.setListener(object : NotesListAdapter.Listener {
            override fun onItemClicked(position: Int) {
                presenter.onNoteSelected(notesListAdapter.getItem(position))
            }

            override fun onItemDeleted(position: Int) {
                presenter.onNoteDeleted(notesListAdapter.getItem(position), position)
            }

            override fun onItemDuplicated(position: Int) {
                presenter.onNoteDuplicated(notesListAdapter.getItem(position))
            }

        })

        rvNotesList.itemAnimator = NotesItemAnimator()
        rvNotesList.adapter = notesListAdapter

        val notesSimpleCallback = NotesSimpleCallback {
            val item = notesListAdapter.getItem(it)
            presenter.onNoteDeleted(item, it)
        }
        ItemTouchHelper(notesSimpleCallback).also { it.attachToRecyclerView(rvNotesList) }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun setData(data: MutableList<Note>) {
        notesListAdapter.setData(data)
    }

    override fun removeNoteAt(position: Int) {
        notesListAdapter.removeAt(position)
    }
}