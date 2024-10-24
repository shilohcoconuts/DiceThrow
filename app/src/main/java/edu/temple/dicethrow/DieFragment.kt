package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random


const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {

    val dieViewModel: DieViewModel by lazy {
        ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }

    lateinit var dieTextView: TextView

    private val KEY = "meowmeow"
    var dieSides: Int = 6
    var rollValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dieViewModel.getDieRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }


        if( dieViewModel.getDieRoll().value == null){
            throwDie()
        }
    }

    fun throwDie() {
        dieViewModel.setDieRoll(Random.nextInt(dieSides)+1)
    }



    companion object{
        fun newInstance(sides: Int) = DieFragment().apply{
           arguments = Bundle().apply {putInt(DIESIDE,sides)}
        }
    }

}