package com.brainbowfx.android.freenotes.presentation.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.presentation.App
import com.brainbowfx.android.freenotes.presentation.abstraction.FloatingActionButtonOwner
import com.brainbowfx.android.freenotes.presentation.adapters.ImagesListAdapter
import com.brainbowfx.android.freenotes.presentation.presenter.ImagesPresenter
import com.brainbowfx.android.freenotes.presentation.presenter.SpeechPresenter
import com.brainbowfx.android.freenotes.presentation.presenter.NotePresenter
import com.brainbowfx.android.freenotes.presentation.utils.ImagesItemKeyProvider
import com.brainbowfx.android.freenotes.presentation.utils.NotesImagesItemDetailsLookup
import com.brainbowfx.android.freenotes.presentation.view.contract.ImagesView
import com.brainbowfx.android.freenotes.presentation.view.contract.NotesEditView
import com.brainbowfx.android.freenotes.presentation.view.contract.SpeechView
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class NotesEditFragment : MvpAppCompatFragment(), SpeechView, NotesEditView, ImagesView {

    companion object {
        private const val REQUEST_TAKE_PHOTO_CODE = 2
        private const val IMAGE_CAPTURE_KEY = "imageUrl"
    }

    @Inject
    lateinit var urlToUriMapper: Mapper<String, Uri>

    @InjectPresenter
    lateinit var speechPresenter: SpeechPresenter

    @InjectPresenter
    lateinit var notePresenter: NotePresenter

    @InjectPresenter
    lateinit var imagesPresenter: ImagesPresenter

    @Inject
    lateinit var floatingActionButtonOwner: FloatingActionButtonOwner

    @Inject
    lateinit var imagesListAdapter: ImagesListAdapter

    private lateinit var tracker: SelectionTracker<String>

    private lateinit var tietTitle: TextInputEditText

    private lateinit var tietInputText: TextInputEditText

    private lateinit var rvImagesList: RecyclerView

    private lateinit var ibDeleteImage: ImageButton

    private var imageCaptureUrl: String? = null

    private var onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            notePresenter.onReturnBack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        App.Instance.activitySubComponent?.inject(speechPresenter)
        App.Instance.activitySubComponent?.inject(notePresenter)
        App.Instance.activitySubComponent?.inject(imagesPresenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.Instance.activitySubComponent?.inject(this)
        return inflater.inflate(R.layout.fragment_note_edit, container, false)
    }

    override fun onStart() {
        super.onStart()
        notePresenter.onStart()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tietTitle = view.findViewById(R.id.tietTitle)
        tietTitle.doOnTextChanged { text, _, _, _ ->
            notePresenter.onTitleChanged(text.toString())
        }
        tietInputText = view.findViewById(R.id.tietInputText)
        tietInputText.doOnTextChanged { text, _, _, _ ->
            notePresenter.onContentTextChanged(text.toString())
        }

        rvImagesList = view.findViewById(R.id.rvImagesList)

        rvImagesList.adapter = imagesListAdapter

        tracker = SelectionTracker.Builder(
            "notesSelection",
            rvImagesList,
            ImagesItemKeyProvider(imagesListAdapter),
            NotesImagesItemDetailsLookup(rvImagesList, imagesListAdapter),
            StorageStrategy.createStringStorage()
        ).build()
        tracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() =
                notePresenter.onImagesSelectionChanged(tracker.selection.size())
        })
        imagesListAdapter.setTracker(tracker)

        imagesListAdapter.setListener { position ->
            val image = imagesListAdapter.getItem(position)
            notePresenter.onImageSelected(image.url)
        }

        ibDeleteImage = view.findViewById(R.id.ibDeleteImages)
        ibDeleteImage.setOnClickListener {
            if (tracker.selection != null && !tracker.selection.isEmpty) {
                val images = imagesListAdapter.getItemsById(tracker.selection.toList())
                notePresenter.onImagesRemoved(images)
            }
        }

        view.findViewById<View>(R.id.ibAddPhoto)
            .setOnClickListener { imagesPresenter.onCameraButtonClicked() }

        view.findViewById<View>(R.id.ibRecordVoice).setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    speechPresenter.onRecordVoiceButtonDown()
                    true
                }
                MotionEvent.ACTION_UP -> {
                    speechPresenter.onRecordVoiceButtonUp()
                    true
                }
                else -> false
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        imageCaptureUrl?.let {
            outState.putString(IMAGE_CAPTURE_KEY, it)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        imageCaptureUrl = savedInstanceState?.getString(IMAGE_CAPTURE_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            imageCaptureUrl?.let(imagesPresenter::onPhotoTaken)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        imagesListAdapter.removeListener()
    }

    //NotesEditView implementation

    override fun setupButton() {
        floatingActionButtonOwner.setupButton(
            R.drawable.ic_arrow_back,
            BottomAppBar.FAB_ALIGNMENT_MODE_END
        ) {
            notePresenter.onReturnBack()
        }
    }

    override fun setTitle(title: String) {
        tietTitle.setText(title)
    }

    override fun setInputText(inputText: String) {
        tietInputText.setText(inputText)
    }

    override fun setImages(imagesPaths: MutableList<Image>) {
        imagesListAdapter.setData(imagesPaths)
    }

    override fun showDeleteButton() {
        ibDeleteImage.visibility = View.VISIBLE
    }

    override fun hideDeleteButton() {
        ibDeleteImage.visibility = View.INVISIBLE
    }

    override fun showSpeechRecognitionError() {
        Toast.makeText(requireContext(), R.string.speech_recognition_error, Toast.LENGTH_SHORT).show()
    }

    override fun placeTextAtCursorPosition(text: String) {
        tietInputText.text?.insert(tietInputText.selectionStart, text)
    }

    override fun showSpeechMessage() {
        Toast.makeText(context, getString(R.string.text_to_speech_notification), Toast.LENGTH_LONG)
            .show()
    }

    //ImagesView implementation methods
    override fun setImage(image: Image) {
        notePresenter.onImageAdded(image)
        tracker.clearSelection()
    }

    override fun checkCameraExistence(url: String) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            .resolveActivity(requireContext().packageManager)
            ?.let {
                imagesPresenter.onCameraExists(url)
            } ?: imagesPresenter.onCameraNotExists()
    }

    override fun takePhoto(url: String) {
        imageCaptureUrl = url
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, urlToUriMapper.map(url))
        startActivityForResult(intent, REQUEST_TAKE_PHOTO_CODE)
    }

    override fun showTakePhotoFailureError() {
        Toast.makeText(context, R.string.take_photo_failure, Toast.LENGTH_LONG).show()
    }

    override fun showNoCameraMessage() {
        Toast.makeText(context, R.string.no_camera_message, Toast.LENGTH_LONG).show()
    }

    override fun showCreateTempFileFailureError() {
        Toast.makeText(context, R.string.create_image_file_failure, Toast.LENGTH_LONG)
            .show()
    }

    override fun showWriteExternalStoragePermissionDenied() {
        Toast.makeText(
            context,
            getString(R.string.write_external_storage_permission_denied),
            Toast.LENGTH_LONG
        ).show()
    }

    @ProvidePresenter
    fun provideNotePresenter(): NotePresenter =
        NotePresenter(arguments?.getLong("id"), arguments?.getBoolean("duplicate"))
}

