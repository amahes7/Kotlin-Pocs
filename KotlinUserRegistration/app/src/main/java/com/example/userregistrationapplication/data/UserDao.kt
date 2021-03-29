package com.example.userregistrationapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.userregistrationapplication.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("Select * from user_table order by id Desc")
    fun getAllUsers(): LiveData<List<User>>

    @Query("Select * from user_table order by id Desc")
    fun getAllUsersFlow(): List<User>

    @Query("Select * from user_table order by id Desc")
    fun getAllUsersByFlow(): Flow<List<User>>

    @Query("Select * from user_table where id =10")
    fun getAllUsersFlow1limit1(): Flow<List<User>>

    @Query("Select * from user_table order by id Asc LIMIT 4 ")
    fun getAllUsersFlowLimit3(): Flow<List<User>>

    @Query("Select * from user_table order by id Desc LIMIT 1 ")
    fun getTopUser(): LiveData<User>

    @Query("Select * from user_table order by id Desc LIMIT 1 ")
    fun getFlowTopUser(): Flow<User>

//    @Query("Delete from user_table where id = (Select id from user_table order by id Desc LIMIT 1) ")
//    fun deleteLastUser(): Flow<User>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete From user_table")
    suspend fun deleteAllUsers()
}