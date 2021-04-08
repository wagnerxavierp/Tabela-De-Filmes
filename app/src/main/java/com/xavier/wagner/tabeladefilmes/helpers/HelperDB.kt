package com.xavier.wagner.tabeladefilmes.helpers

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(
        context: Context
) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object {
        private val NOME_BANCO = "filme"
        private val VERSAO_ATUAL = 1
    }

    val TABLE_NAME_FAVORITOS = "favoritos"
    val COLUMNS_ID_FAVORITO = "id"
    val COLUMNS_ID_FILME_FAVORITO = "idFilme"

    val DROP_TABLE_FAVORITOS = "DROP TABLE IF EXISTS $TABLE_NAME_FAVORITOS"
    val CREATE_TABLE_FAVORITOS = "CREATE TABLE $TABLE_NAME_FAVORITOS (" +
            "$COLUMNS_ID_FAVORITO INTEGER NOT NULL," +
            "$COLUMNS_ID_FILME_FAVORITO INTEGER NOT NULL," +
            "" +
            "PRIMARY KEY($COLUMNS_ID_FAVORITO AUTOINCREMENT))"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_FAVORITOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(DROP_TABLE_FAVORITOS)
        }
        onCreate(db)
    }

    fun isFilmeFavorito(id: Int): Boolean{
        val db = readableDatabase ?: return false
        val sql = "SELECT * FROM $TABLE_NAME_FAVORITOS WHERE $COLUMNS_ID_FILME_FAVORITO LIKE '$id'"
        val cursor = db.rawQuery(sql, null) ?: return false
        if(cursor.count == 1){
            db.close()
            return true
        }
        db.close()
        return false
    }

    fun salvarFilmeFavorito(idFilme: Int){
        val db = writableDatabase ?: return
        val sql = "INSERT INTO $TABLE_NAME_FAVORITOS ($COLUMNS_ID_FILME_FAVORITO) VALUES (?)"
        db.execSQL(sql, arrayOf(idFilme))
        db.close()
    }

    fun removerFavorito(idFilme: Int){
        val db = writableDatabase ?: return
        val sql = "DELETE FROM $TABLE_NAME_FAVORITOS WHERE $COLUMNS_ID_FILME_FAVORITO LIKE ?"
        db.execSQL(sql, arrayOf(idFilme))
        db.close()
    }

}