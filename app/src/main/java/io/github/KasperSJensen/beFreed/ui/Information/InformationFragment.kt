package io.github.KasperSJensen.beFreed.ui.Information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.KasperSJensen.beFreed.R

class InformationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_information, container, false)

        val wallOfText = rootView.findViewById<TextView>(R.id.wallOfText)

        val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Donec nulla ipsum, tincidunt nec sapien in, pellentesque mollis augue. " +
                "In placerat viverra consequat. Ut massa velit, ullamcorper suscipit elit a, " +
                "tempor cursus sapien. Vestibulum nisl ante, suscipit sed lectus nec, hendrerit " +
                "condimentum lectus. Nam aliquet non orci eget ornare. Ut vehicula magna vitae nibh " +
                "lacinia tempor. Vivamus viverra neque ac mauris consectetur, vitae pharetra eros pulvinar. " +
                "Aliquam risus elit, semper quis ullamcorper et, vehicula non odio. Aenean tristique consectetur " +
                "bibendum. Aliquam eu mattis est, vel viverra mi. Donec sagittis lorem leo, a maximus erat fermentum at. " +
                "Nulla a vestibulum lectus. Pellentesque laoreet iaculis tortor eu facilisis.\n" +
                "\n" +
                "Donec fringilla vestibulum odio sollicitudin tincidunt. Etiam sed neque aliquam arcu vestibulum blandit vel nec tortor. " +
                "Duis dolor odio, viverra at rutrum ut, ultricies interdum ipsum. Suspendisse sed metus vitae sem lacinia feugiat. " +
                "Cras a augue blandit erat mollis lacinia. Nunc sit amet odio sed sapien iaculis placerat. " +
                "Quisque laoreet, nisl rhoncus porttitor pharetra, quam leo faucibus ex, vitae posuere ex augue eu arcu. " +
                "Proin non odio et urna elementum molestie. Suspendisse hendrerit eleifend dictum. " +
                "Vestibulum interdum consectetur lectus, at laoreet urna ultricies id. " +
                "Maecenas lobortis, felis non cursus cursus, erat nulla accumsan turpis, quis."

        wallOfText.text = text

        return rootView
    }
}