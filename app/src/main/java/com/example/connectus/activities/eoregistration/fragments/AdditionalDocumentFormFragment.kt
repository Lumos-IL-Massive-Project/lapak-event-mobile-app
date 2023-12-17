package com.example.connectus.activities.eoregistration.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.eoregistration.adapters.RecyclerViewDocumentAdapter
import com.example.connectus.activities.eoregistration.models.DocumentData
import com.example.connectus.databinding.FragmentAdditionalDocumentFormBinding

class AdditionalDocumentFormFragment : Fragment() {
    private var binding: FragmentAdditionalDocumentFormBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdditionalDocumentFormBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUploadDocumentButton()
        initDocumentList()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedDocumentUri: Uri? = data?.data
            Log.d("DocumentPicker", "Selected Document Path: $selectedDocumentUri")
        }
    }

    private fun initUploadDocumentButton() {
        binding?.fbUploadDocument?.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/pdf"

            startActivityForResult(intent, PICK_DOCUMENT_REQUEST_CODE)
        }
    }

    private fun initDocumentList() {
        val data = listOf(
            DocumentData(1, "test 1.jpg", ""),
            DocumentData(2, "test 2.pdf", ""),
            DocumentData(3, "test 3.png", ""),
            DocumentData(4, "test 4.pdf", ""),
        )

        binding?.rvDocumentList?.adapter = RecyclerViewDocumentAdapter(data)
        binding?.rvDocumentList?.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        private const val PICK_DOCUMENT_REQUEST_CODE = 1
    }
}