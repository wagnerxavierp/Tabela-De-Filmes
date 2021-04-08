package com.xavier.wagner.tabeladefilmes.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperDB(
        context: Context
) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL) {

    companion object {
        private val NOME_BANCO = "filme"
        private val VERSAO_ATUAL = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FilmesFavoritos.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion != newVersion){
            db?.execSQL(FilmesFavoritos.DROP_TABLE)
        }
        onCreate(db)
    }

    fun isFilmeFavorito(id: Int): Boolean{
        val db = readableDatabase ?: return false
        val sql =
                "SELECT * FROM ${FilmesFavoritos.TABLE_NAME} " +
                        "WHERE ${FilmesFavoritos.COLUMNS_FILME_ID} LIKE '$id'"
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
        val sql =
                "INSERT INTO ${FilmesFavoritos.TABLE_NAME} (${FilmesFavoritos.COLUMNS_FILME_ID}) " +
                        "VALUES (?)"
        db.execSQL(sql, arrayOf(idFilme))
        db.close()
    }

    fun removerFavorito(idFilme: Int){
        val db = writableDatabase ?: return
        val sql =
                "DELETE FROM ${FilmesFavoritos.TABLE_NAME} " +
                "WHERE ${FilmesFavoritos.COLUMNS_FILME_ID} LIKE ?"
        db.execSQL(sql, arrayOf(idFilme))
        db.close()
    }
}

class FilmesFavoritos{
    companion object{
        val TABLE_NAME = "favoritos"
        val COLUMNS_ID = "id"
        val COLUMNS_FILME_ID = "idFilme"

        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_ID INTEGER NOT NULL," +
                "$COLUMNS_FILME_ID INTEGER NOT NULL," +
                "" +
                "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT))"
    }
}