package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.interactor.DeleteImages
import com.brainbowfx.android.freenotes.domain.interactor.SaveNote
import com.brainbowfx.android.freenotes.domain.interactor.GetNote
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.presentation.view.contract.NotesEditView
import com.brainbowfx.android.freenotes.presentation.whenNotNullOrEmpty
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.collections.ArrayList

@InjectViewState
class NotePresenter(private val argId: Long?, private val argDuplicate: Boolean?) :
    ScopedPresenter<NotesEditView>() {

    @Inject
    lateinit var imageViewer: ImageViewer

    @Inject
    lateinit var getNote: GetNote

    @Inject
    lateinit var saveNote: SaveNote

    @Inject
    lateinit var notesRouter: NotesRouter

    @Inject
    lateinit var deleteImages: DeleteImages

    @field:[Inject Named(DATETIME_NAMED_ID)]
    lateinit var simpleDateFormat: SimpleDateFormat

    private var note: Note? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (argId == null || argId == 0L) {
            note = Note(dateTime = simpleDateFormat.format(Date()))
        } else {
            launch {
                note = getNote.execute(argId)?.also(this@NotePresenter::initViewFields)
                if (argDuplicate == true) {
                    note?.id = 0
                }
            }
        }

        viewState.setupButton()
    }

    private fun initViewFields(note: Note) {
        viewState.setInputText(note.text)
        viewState.setTitle(note.title)
        viewState.setImages(ArrayList(note.images))
    }

    fun onTitleChanged(titleString: String) {
        note?.title = titleString
    }

    fun onContentTextChanged(bodyString: String) {
        note?.text = bodyString
    }

    fun onImageAdded(image: Image) {
        note?.images?.let {
            it.add(image)
            viewState.setImages(ArrayList(it))
        }
    }

    fun onImagesRemoved(images: List<Image>) {
        launch {
            deleteImages.execute(images)
            note?.images?.let {
                it.removeAll(images)
                viewState.setImages(ArrayList(it))
                viewState.hideDeleteButton()
            }
        }
    }

    fun onReturnBack() {
        note?.whenNotNullOrEmpty {
            launch {
                saveNote.execute(it)
                notesRouter.returnBack()
            }
        }

    }

    fun onImageSelected(url: String) = imageViewer.showImage(url)

    fun onImagesSelectionChanged(itemsCount: Int) {
        if (itemsCount > 0) viewState.showDeleteButton()
        else viewState.hideDeleteButton()
    }

}