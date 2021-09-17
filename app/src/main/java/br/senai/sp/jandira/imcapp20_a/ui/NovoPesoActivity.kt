package br.senai.sp.jandira.imcapp20_a.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import br.senai.sp.jandira.imcapp20_a.R
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_novo_peso.*
import kotlinx.android.synthetic.main.activity_novo_peso.view.*

class NovoPesoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_peso)

        tv_sair.setOnClickListener(){
            abrirNovaDashBoard()
        }
    }

    private fun abrirNovaDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)

    }
}


