package com.brainbowfx.android.freenotes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.adapters.NotesListAdapter
import com.brainbowfx.android.freenotes.presentation.presenter.NotesListPresenter
import com.brainbowfx.android.freenotes.presentation.utils.NotesSimpleCallback

class NotesListFragment : BaseListFragment() {

    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.Instance.activitySubcomponent?.inject(this)
        App.Instance.activityPerInstanceSubcomponent?.inject(presenter)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

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

        val notesSimpleCallback = NotesSimpleCallback {
            val item = notesListAdapter.getItem(it)
            presenter.onNoteDeleted(item, it)
        }
        ItemTouchHelper(notesSimpleCallback).also { it.attachToRecyclerView(rvNotesList) }

        super.onViewCreated(view, savedInstanceState)
    }
}