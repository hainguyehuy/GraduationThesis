package com.example.graduationthesis.data.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.graduationthesis.data.model.SamPlePD
import com.example.graduationthesis.data.model.User

class DatabaseHelper( context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object {
        private const val DATABASE_NAME = "HaiDataBase.db"
        private const val DATABASE_VERSION = 1
        //user
        private const val TABLE_USERS = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_dateBirth = "dateBirth"
        private const val COLUMN_gender = "gender"
        private const val COLUMN_numberPhone = "numberPhone"
        private const val COLUMN_passWord = "passWord"
        private const val COLUMN_imgUser = "imgUser"

        //product
        private const val TABLE_PRODUCT = "Product"
        private const val COLUMN_productID = "idPD"
        private const val COLUMN_productName = "namePD"
        private const val COLUMN_productPrice = "pricePD"
        private const val COLUMN_productThumbnail = "urlPD"
        private const val COLUMN_productDescription = "titlePD"
        private const val COLUMN_categoryID = "numberPhone"
        private const val COLUMN_brandID = "passWord"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUser = ("CREATE TABLE $TABLE_USERS ($COLUMN_ID TEXT PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT," +
                "$COLUMN_dateBirth TEXT, $COLUMN_gender TEXT, $COLUMN_numberPhone TEXT, $COLUMN_passWord TEXT, $COLUMN_imgUser TEXT )")


        val createTableProduct = ("CREATE TABLE $TABLE_PRODUCT ($COLUMN_productID TEXT PRIMARY KEY, $COLUMN_productName TEXT, $COLUMN_productPrice TEXT," +
                "$COLUMN_productThumbnail TEXT, $COLUMN_productDescription TEXT, $COLUMN_categoryID FOREIGN KEY TEXT, $COLUMN_brandID FOREIGN KEY TEXT )")
        db?.execSQL(createTableUser)
        db?.execSQL(createTableProduct)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCT")
        onCreate(db)
    }
    fun addUser(user: User) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_ID, user.id)
            put(COLUMN_NAME, user.name)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_dateBirth, user.dateBirth)
            put(COLUMN_gender, user.gender)
            put(COLUMN_numberPhone, user.numberPhone)
            put(COLUMN_passWord, user.passWord)
            put(COLUMN_imgUser, user.imgUser)
        }
        db.insert(TABLE_USERS, null, contentValues)
        db.close()
    }
    fun addProduct(product: SamPlePD) {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_productID, product.idPD)
            put(COLUMN_productName, product.namePD)
            put(COLUMN_productPrice, product.pricePD)
            put(COLUMN_productThumbnail, product.urlPD)
            put(COLUMN_productDescription, product.titlePD)

        }
        db.insert(TABLE_PRODUCT, null, contentValues)
        db.close()
    }
}