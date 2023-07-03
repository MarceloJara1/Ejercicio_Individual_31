package com.example.ejercicioindividual31

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name="name") val name:String?,
    @ColumnInfo(name="userName") val userName:String?,
    @ColumnInfo(name="age") val age:Int?
)