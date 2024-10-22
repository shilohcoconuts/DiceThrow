package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random


const val DIESIDE = "sidenumber"

class DieFragment : Fragment() {

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
        savedInstanceState?.run{
            rollValue = getInt(KEY)
        }
        if(rollValue!=0){
            dieTextView.text = rollValue.toString()
        }else
        throwDie()
    }

    fun throwDie() {
        var roll = Random.nextInt(dieSides)+1
        dieTextView.text = roll.toString()
        rollValue = roll
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY,rollValue)
    }

    companion object{
        fun newInstance(sides: Int) = DieFragment().apply{
           arguments = Bundle().apply {putInt(DIESIDE,sides)}
        }
    }

}