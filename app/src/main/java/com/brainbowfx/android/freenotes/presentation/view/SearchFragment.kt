package com.brainbowfx.android.freenotes.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.adapters.SearchAdapter
import com.brainbowfx.android.freenotes.presentation.presenter.SearchPresenter
import com.brainbowfx.android.freenotes.presentation.toColor
import com.brainbowfx.android.freenotes.presentation.view.contract.SearchView
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class SearchFragment : MvpAppCompatFragment(), SearchView {

    private lateinit var rvSearchResultList: RecyclerView
    private lateinit var tietSearchInput: TextInputEditText

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.Instance.activitySubComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchInput(view)
        initResultsList(view)
    }

    private fun initSearchInput(view: View) {
        val selectionColor = requireContext().toColor(R.color.colorAccent)
        tietSearchInput = view.findViewById(R.id.tietSearch)
        tietSearchInput.doAfterTextChanged {
            val query = it.toString()
            searchPresenter.onQueryChanged(query)
            searchAdapter.setSelection(query, selectionColor)
        }
    }

    private fun initResultsList(view: View) {
        rvSearchResultList = view.findViewById(R.id.rvResultNoteList)
        rvSearchResultList.adapter = searchAdapter
    }


    override fun setResult(notes: List<Note>) {
        searchAdapter.setData(notes)
    }

}