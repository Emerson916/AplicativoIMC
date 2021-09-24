package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.utils.converterBase64ParaBitMap
import br.senai.sp.jandira.imcapp20_a.utils.converterBitMapParaBase64
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_imc.*
import kotlinx.android.synthetic.main.activity_imc.ed_peso
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_ncd.*

//Login Teste

//email : emerteste@gmail.com
//senha : 123

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        // Removendo a appBar
        getSupportActionBar()!!.hide()

        // Se o peso for 0, abre o dialogAviso
        preencherDashBoard()
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val peso = dados.getInt("peso", 0)

        if (peso == 0){
            dialogAviso()
        }


        tv_logout.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putBoolean("lembrar", false)
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    // Função dialog/modal

    fun dialogAviso() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Aviso!!")
        alert.setMessage("Ops, acho que você não cadastrou seu peso!, altere alguns dados, para continuarmos")
        alert.setPositiveButton("Alterar", { dialogInterface: DialogInterface, i: Int ->
            setContentView(R.layout.activity_novo_peso)
        })
        alert.setNegativeButton("Agora não", { dialogInterface: DialogInterface, i: Int ->
            //finish()
        })
        alert.show()
    }

    private fun preencherDashBoard() {
        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        tv_profile_name.text = dados.getString("nome", "")
        tv_profile_occupation.text = dados.getString("profissao", "")
        tv_weight.text = dados.getInt("peso", 0).toString()
        tv_age.text = dados.getString("idade", "")

        val imageBase64 = dados.getString("foto", "")
        val imageBitmap = converterBase64ParaBitMap(imageBase64)

        iv_profile.setImageBitmap(imageBitmap)

        // *** Colocar foto do Github no ImageView
//        val url = "https://avatars.githubusercontent.com/u/77053593?v=4"
//        Glide.with(this).load(url).into(iv_profile)

    }



}


