package com.arkademy.ankasa.onboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.item_on_board.view.*

class OnBoardAdapter(private val onBoardList: ArrayList<OnBoardModel>): RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    class OnBoardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(listOnBoard: OnBoardModel){
            with(itemView){
                iv_on_board.setImageResource(listOnBoard.image)
                tv_onboard_title.text = listOnBoard.title
                tv_on_board_desc.text = listOnBoard.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_on_board, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(onBoardList[position])
    }

    override fun getItemCount(): Int {
        return onBoardList.size
    }
}