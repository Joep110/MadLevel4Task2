package com.example.madlevel4task1

import android.content.Context
import com.example.madlevel4task2.Game
import com.example.madlevel4task2.GameDao
import com.example.madlevel4task2.GameRoomDatabase

class GameRepository(context: Context) {

    private val GameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        GameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return GameDao.getAllGames()
    }

    suspend fun insertGame(Game: Game) {
        GameDao.insertGame(Game)
    }

    suspend fun deleteAllGames() {
        GameDao.deleteAllGames()
    }

}