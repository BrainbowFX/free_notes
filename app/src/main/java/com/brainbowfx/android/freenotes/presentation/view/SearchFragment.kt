package com.brainbowfx.android.freenotes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.abstraction.FloatingActionButtonOwner
import com.brainbowfx.android.freenotes.presentation.adapters.SearchAdapter
import com.brainbowfx.android.freenotes.presentation.presenter.SearchPresenter
import com.brainbowfx.android.freenotes.presentation.toColor
import com.brainbowfx.android.freenotes.presentation.view.contract.SearchView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class SearchFragment : MvpAppCompatFragment(), SearchView {

    private lateinit var rvSearchResultList: RecyclerView
    private lateinit var tietSearchInput: TextInputEditText
    private val selectionColor: Int by lazy {
        requireContext().toColor(R.color.colorAccent)
    }

    @Inject
    lateinit var floatingActionButtonOwner: FloatingActionButtonOwner

    @InjectPresenter
    lateinit var searchPresenter: SearchPresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var itemDecoration: RecyclerView.ItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.Instance.activitySubComponent?.let {
            it.inject(this)
            it.inject(searchPresenter)
        }
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

    override fun onStart() {
        super.onStart()
        searchPresenter.onStart()
    }

    private fun initSearchInput(view: View) {
        tietSearchInput = view.findViewById(R.id.tietSearch)
        tietSearchInput.doOnTextChanged { text, _, _, _ ->
            val query = text.toString()
            searchPresenter.onQueryChanged(query)
        }
    }

    private fun initResultsList(view: View) {
        rvSearchResultList = view.findViewById(R.id.rvResultNoteList)
        rvSearchResultList.adapter = searchAdapter.apply {
            setListener {
                searchPresenter.onNoteSelected(getItemId(it))
            }
        }
        rvSearchResultList.addItemDecoration(itemDecoration)
    }


    override fun setResult(query: String, notes: List<Note>) {
        searchAdapter.setData(notes)
        searchAdapter.setSelection(query, selectionColor)
    }

    override fun setupButton() {
        floatingActionButtonOwner.setupButton(
            R.drawable.ic_arrow_back,
            fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        ) {
            searchPresenter.onFloatingButtonClick()
        }
    }

}