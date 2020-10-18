package com.arkademy.ankasa.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityOnBoardBinding
import com.arkademy.ankasa.login.LoginActivity
import com.arkademy.ankasa.register.RegisterActivity

class OnBoardActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityOnBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_board)
        OnBoardModel.getOnBoard()
        setRecyclerView()

        binding.btnCreateAccount.setOnClickListener(this)
        binding.btnSignIn.setOnClickListener(this)
    }

    private fun setRecyclerView() {
        binding.apply {
            val offScreenPageLimit = 2
            vpOnBoard.adapter = OnBoardAdapter(OnBoardModel.getOnBoard())
            vpOnBoard.setPageTransformer(OnBoardTransition(offScreenPageLimit))
            vpIndicator.setViewPager(vpOnBoard)
        }
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when(p0) {
                btnCreateAccount -> {startActivity((Intent(this@OnBoardActivity, RegisterActivity::class.java)))}
                btnSignIn -> {startActivity(Intent(this@OnBoardActivity, LoginActivity::class.java))}
            }
        }
    }
}