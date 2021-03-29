package com.example.roomwordexample.dao

import android.os.FileObserver.DELETE
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomwordexample.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("Select * FROM word_table ORDER BY word ASC")
    fun getAllWords(): Flow<List<Word>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Query("DELETE from word_table")
    suspend fun deleteAll();

}