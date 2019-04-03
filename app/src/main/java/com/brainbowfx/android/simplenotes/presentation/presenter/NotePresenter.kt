package com.brainbowfx.android.simplenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.interactor.AddNote
import com.brainbowfx.android.simplenotes.domain.interactor.GetNote
import com.brainbowfx.android.simplenotes.domain.interactor.UpdateNote
import com.brainbowfx.android.simplenotes.presentation.view.contract.NotesEditView
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

    @field:[Inject Named("DateTime")]
    lateinit var simpleDateFormat: SimpleDateFormat

    private lateinit var note: Note

    private var job: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (argId != null && argDuplicate != null) job = start(argId, argDuplicate)

    }

    fun start(id: Long, duplicate: Boolean): Job = GlobalScope.launch(dispatchersProvider.getMainDispatcher()) {
        withContext(dispatchersProvider.getIODispatcher()) {
            note = getNote(id)
            note.id = saveNote(duplicate)
        }

        viewState.setInputText(note.text)
        viewState.setTitle(note.title)
        viewState.setImages(note.imagePaths)
        viewState.setArgs(note.id, false)
    }

    fun onStop(title: String, inputText: String, imagesPaths: MutableList<String>) {
        note.title = title
        note.text = inputText
        note.imagePaths = imagesPaths

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

    fun updateNote(note: Note): Job =
        GlobalScope.launch(dispatchersProvider.getIODispatcher()) {
            updateNote.execute(note)
        }

    suspend fun getNote(id: Long): Note =
        if (id != -1L) getNote.execute(id) else Note(dateTime = simpleDateFormat.format(Date()))

    suspend fun saveNote(duplicate: Boolean): Long {
        if (duplicate) note.id = -1L
        return if (note.id == -1L) addNote.execute(note) else note.id
    }
}