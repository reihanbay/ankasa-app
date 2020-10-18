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
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            )
            onBoard.add(firstOnBoard)

            val secondOnBoard = OnBoardModel(
                R.drawable.ic_onboard_illustration,
                "GET STARTED",
                "orem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
            )
            onBoard.add(secondOnBoard)
            return onBoard
        }
    }
}