package com.example.water13.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.example.water13.component.toast
import com.example.water13.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.cards_vertical.*
import timber.log.Timber


class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding
    val cardList: MutableList<ImageView> by lazy {
        mutableListOf<ImageView>().apply {
            add(card0)
            add(card1)
            add(card2)
            add(card3)
            add(card4)
            add(card5)
            add(card6)
            add(card7)
            add(card8)
            add(card9)
            add(card10)
            add(card11)
            add(card12)
        }
    }

    val viewModel: GameViewModel by lazy {
        ViewModelProviders.of(activity!!).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.model = viewModel
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.onNextClicked()
        binding.btNext.setOnClickListener {
            viewModel.onNextClicked()
        }
        val resources = context?.resources
        viewModel.cardsImage.observe(this, Observer {
            for (i in 0..12) {
                Timber.d("heihei ${it[i]}")
                val resourceId = resources?.getIdentifier(
                    it[i], "drawable",
                    context?.packageName
                )
                val image = resources?.getDrawable(resourceId!!, null)
                Glide.with(activity!!).load(image).override(SIZE_ORIGINAL).into(cardList[i])
            }
        })
        viewModel.message.observe(this, Observer {
            toast(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeAuto()
    }
}
