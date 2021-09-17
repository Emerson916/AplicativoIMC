package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Biometria

import br.senai.sp.jandira.imcapp20_a.utils.converterBitmapParaByteArray

class BiometriaDao(val context: Context, val biometria: Biometria?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("peso", biometria!!.peso)
        dados.put("nivel_atividade", biometria.nivelAtiviade)
        dados.put("data_pesagem", biometria.dataPesagem.toString())
        dados.put("id_usuario", biometria.usuario.toString())

        // *** Executar o comando de gravação
        db.insert("tb_biometria", null, dados)

        db.close()
    }
}