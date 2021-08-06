package br.senai.sp.jandira.imcapp20_a.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_usuario.*

class NovoUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        bt_gravar.setOnClickListener {
            // *** Criar o sharedPreferences
//            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
//
//            val editor = dados.edit()
//            editor.putString("nome", et_nome.text.toString())
//            editor.putString("profissao", et_profissao.text.toString())
//            editor.putInt("peso", et_peso.text.toString().toInt())
//            editor.putInt("idade", et_data_nascimento.text.toString().toInt())
//            editor.putString("email", et_email.text.toString())
//            editor.putString("senha", et_senha.text.toString())
//            editor.apply()

            /** Gravar um novo usuario no Banco de Dados**/

            val usuario = Usuario(
            0,
            et_email.text.toString(),
            et_senha.text.toString(),
            et_nome.text.toString(),
            et_profissao.text.toString(),
            et_altura.text.toString().toDouble(),
            et_data_nascimento.toString(),
            'M',
            null)

            val dao = UsuarioDao(this, usuario)
            dao.gravar()

            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

            finish()

        }

    }
}