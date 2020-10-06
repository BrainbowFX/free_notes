package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.interactor.AddNote
import com.brainbowfx.android.freenotes.domain.interactor.GetNote
import com.brainbowfx.android.freenotes.domain.interactor.UpdateNote
import com.brainbowfx.android.freenotes.presentation.view.contract.NotesEditView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class NotePresenter(private val argId: Long?, private val argDuplicate: Boolean?) : MvpPresenter<NotesEditView>() {

    @Inject
    lateinit var dispatchersProvider: CoroutineDispatchersProvider

    @Inject
    lateinit var imageViewer: ImageViewer

    @Inject
    lateinit var getNote: GetNote

    @Inject
    lateinit var addNote: AddNote

    @Inject
    lateinit var updateNote: UpdateNote

    @field:[Inject Named(DATETIME_NAMED_ID)]
    lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var note: Note

    private var job: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (argId != null && argDuplicate != null) job = start(argId, argDuplicate)
    }

    private fun start(id: Long, duplicate: Boolean): Job =
        GlobalScope.launch(dispatchersProvider.getMainDispatcher()) {
            note = withContext(dispatchersProvider.getIODispatcher()) { prepareNote(getNote(id), duplicate) }
            initViewFields(note)
        }

    private fun initViewFields(note:Note) {
        viewState.setInputText(note.text)
        viewState.setTitle(note.title)
        viewState.setImages(note.images)
        viewState.setArgs(note.id, false)
    }

    fun onSave(title: String, inputText: String, images: MutableList<Image>) {
        note.title = title
        note.text = inputText
        note.images = images

        updateNote(note)
    }

    fun onImageSelected(url: String) = imageViewer.showImage(url)

    fun onImagesSelectionChanged(itemsCount: Int) {
        if (itemsCount > 0) viewState.showDeleteButton()
        else viewState.hideDeleteButton()
    }

    fun onImagesDeleted(ids: List<Long>) {
        viewState.removeImages(ids)
    }

    private suspend fun prepareNote(note: Note, duplicate: Boolean): Note {
        if (duplicate) note.id = 0L
        if (note.id == 0L) note.id = addNote.execute(note)
        return note
    }

    private fun updateNote(note: Note): Job =
        GlobalScope.launch(dispatchersProvider.getIODispatcher()) {
            updateNote.execute(note)
        }

    private suspend fun getNote(id: Long): Note =
        if (id != -1L) getNote.execute(id) else Note(dateTime = simpleDateFormat.format(Date()))

}