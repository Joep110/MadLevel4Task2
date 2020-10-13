package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Game_Table")
class Game (

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "computer_move")
    var computerMove: Short,

    @ColumnInfo(name = "player_move")
    var playerMove: Short,

    @ColumnInfo(name = "result")
    var result: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)