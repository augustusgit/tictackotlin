package com.augustus.tictactoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        val buSelected = view as Button

        var cellId = 0

        when(buSelected.id){
            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9
        }

        //Log.d("buClick", buSelected.id.toString())
        //Log.d("buClick: cellId", cellId.toString())

        playGame(cellId, buSelected)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    fun playGame(cellId:Int, buSelected:Button){
        if( activePlayer == 1){
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoplay()
        }else{
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            activePlayer = 1
        }

        buSelected.isEnabled = false

        checkWinner()
    }

    fun checkWinner() {

        var winner = -1

        // row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        // row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        // row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }


        // column 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }


        // column 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(5)){
            winner = 2
        }

        // column 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        if(winner == 1){
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
        }else if(winner == 2){
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
        }
    }

    fun autoplay(){
        var emptyCell = ArrayList<Int>()

        for( cellId in 1..9){
            if (!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCell.add(cellId)
            }
        }

        var r = Random()
        val randIndex = r.nextInt(emptyCell.size)
        val cellId = emptyCell[randIndex]

        var buSelected:Button?
        buSelected = when(cellId){
            1 -> findViewById(R.id.bu1)
            2 -> findViewById(R.id.bu2)
            3 -> findViewById(R.id.bu3)
            4 -> findViewById(R.id.bu4)
            5 -> findViewById(R.id.bu5)
            6 -> findViewById(R.id.bu6)
            7 -> findViewById(R.id.bu7)
            8 -> findViewById(R.id.bu8)
            9 -> findViewById(R.id.bu9)
            else -> {findViewById(R.id.bu1)}
        }

        if (buSelected != null) {
            playGame(cellId, buSelected)
        }
    }
}