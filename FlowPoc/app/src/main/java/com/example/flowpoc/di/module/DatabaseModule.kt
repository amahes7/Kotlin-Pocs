//package com.example.flowpoc.di.module
//
//import android.content.Context
//import androidx.room.Room
//import com.example.flowpoc.db.UsersDao
//import com.example.flowpoc.db.UsersDatabase
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//@Module
//class DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun providesRoom(appContext : Context) : UsersDatabase {
//        return Room
//            .databaseBuilder(appContext, UsersDatabase::class.java, "database-users")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideUserDao(userDatabase: UsersDatabase): UsersDao {
//        return userDatabase.usersDao()
//    }
//}