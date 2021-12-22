package com.example.chicagoxleftovers

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentContainer
import kotlinx.android.synthetic.main.fragment_custom_dialog.view.*
import kotlinx.android.synthetic.main.ulasan_item.*
import java.util.zip.Inflater



class CustomDialogFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_custom_dialog, container,false)

        rootView.rbRating.rating = 2.5f
        rootView.rbRating.stepSize = .5f

        rootView.rbRating.setOnRatingBarChangeListener{
                ratingBar, rating, fromUser ->

            Toast.makeText(requireActivity(), "Rating, $rating", Toast.LENGTH_SHORT).show()


        }

        rootView.btBatal.setOnClickListener{
            dismiss()
        }

        rootView.btTambahUlasan.setOnClickListener {
            dismiss()
        }

        return rootView
    }

}
