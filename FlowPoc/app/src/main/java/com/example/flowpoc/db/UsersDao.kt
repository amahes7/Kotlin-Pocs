//package com.example.flowpoc.db
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//abstract class UsersDao {
//
//    @Query("SELECT * FROM Users")
//    abstract fun getAllUsers(): Flow<List<Users>>
//
//    @Query("SELECT * FROM Users order by id Desc limit 1")
//    abstract fun getOneUser(): Flow<List<Users>>
//
//    @Query("SELECT * FROM Users order by id Desc limit 2")
//    abstract fun getTwoUsers(): Flow<List<Users>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insertUsers(users: List<Users>)
//
//    @Query("DELETE FROM Users")
//    abstract fun deleteAllUsers()
//
//    @Query("SELECT * FROM Users WHERE id = :id")
//    abstract fun getUser(id: String): Flow<Users>
//
//}