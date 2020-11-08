package com.arkademy.ankasa.onboard

import com.arkademy.ankasa.R

data class OnBoardModel(
    val image: Int,
    val title: String,
    val description: String,
) {
    companion object {
        fun getOnBoard(): ArrayList<OnBoardModel> {
            val onBoard = ArrayList<OnBoardModel>()

            val firstOnBoard = OnBoardModel(
                R.drawable.ic_onboard_illustration,
                "GET STARTED",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
            )
            onBoard.add(firstOnBoard)

            val secondOnBoard = OnBoardModel(
                R.drawable.ic_onboard_illustration,
                "GET STARTED",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
            )
            onBoard.add(secondOnBoard)
            return onBoard
        }
    }
}