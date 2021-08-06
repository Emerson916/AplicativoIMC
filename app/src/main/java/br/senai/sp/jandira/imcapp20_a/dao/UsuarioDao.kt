package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Usuario

class UsuarioDao(val context: Context, val usuario: Usuario) {

    val dbHelper = ImcDataBase.getDatabase(context)

    private fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("nome", usuario.nome)
        dados.put("profissao", usuario.profissao)
        dados.put("email", usuario.email)
        dados.put("senha", usuario.senha)
        dados.put("altura", usuario.altura)
        dados.put("data_nascimento", usuario.dataNascimento.toString())
        dados.put("sexo", usuario.sexo.toString())

        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)

        db.close()
    }

}