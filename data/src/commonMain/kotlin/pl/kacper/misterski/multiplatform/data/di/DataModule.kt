package pl.kacper.misterski.multiplatform.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


//TODO K da sie tak db?
val dataModule = module {
    singleOf(::Greeting)
}