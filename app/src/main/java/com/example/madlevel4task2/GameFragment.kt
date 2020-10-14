package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.madlevel4task1.GameRepository
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        setScore()

        ivPaperClick.setOnClickListener{
            addGame(1)
        }

        ivRockClick.setOnClickListener {
            addGame(2)
        }

        ivScissorClick.setOnClickListener {
            addGame(3)
        }
    }

    private fun addGame(playerMove: Int) {
        val computerMove = (0..4).random()
        val result = checkGameResults(playerMove, computerMove)
        updateUI(playerMove, computerMove, result)
        mainScope.launch {
            val game = Game(
                date = Date(),
                computerMove = computerMove.toShort(),
                playerMove = playerMove.toShort(),
                result = result
            )
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }

    }

    private fun updateUI(playerMove: Int, computerMove: Int, result: String) {
        setScore()
        tvWinner.text = result
        when (computerMove) {
            1 -> ivComputerResult.setImageResource(R.drawable.paper)
            2 -> ivComputerResult.setImageResource(R.drawable.rock)
            3 -> ivComputerResult.setImageResource(R.drawable.scissors)
        }
        when (playerMove) {
            1 -> ivYouResult.setImageResource(R.drawable.paper)
            2 -> ivYouResult.setImageResource(R.drawable.rock)
            3 -> ivYouResult.setImageResource(R.drawable.scissors)
        }
    }

    private fun setScore() {
        var gameDraws = 0
        var gameWin = 0
        var gameLose = 0
        mainScope.launch {
            gameDraws = withContext(Dispatchers.IO) {
                gameRepository.countDraws()
            }
            gameWin = withContext(Dispatchers.IO) {
                gameRepository.countWins()
            }
            gameLose = withContext(Dispatchers.IO) {
                gameRepository.countLose()
            }
            tvResult.text = getString(R.string.statistic_score, gameDraws, gameWin, gameLose)
        }
    }

    private fun checkGameResults(playerMove: Int, computerMove: Int) : String {
        var result: String

        if (playerMove == computerMove) {
            result = getString(R.string.draw)
        } else if ((playerMove == 2 && computerMove == 3) ||
            (playerMove == 1 && computerMove == 2) ||
            (playerMove == 3 && computerMove == 1)) {
            result = getString(R.string.win)
        } else {
            result = getString(R.string.lose)
        }

        return result
    }
}