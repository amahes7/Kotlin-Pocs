package com.example.flowpoc.di.module

import com.example.flowpoc.activity.main.MainActivity
import com.example.flowpoc.di.module.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): MainActivity

}