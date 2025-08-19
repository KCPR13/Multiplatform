package pl.kacper.misterski.multiplatform

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import pl.kacper.misterski.multiplatform.data.di.dataModules
import pl.kacper.misterski.multiplatform.domain.di.domainModules
import pl.kacper.misterski.multiplatform.ui.di.uiModules

// TODO K naming for ios
class LoggerForIOS : Logger(){
    override fun display(level: Level, msg: MESSAGE) {
        println(msg)
    }

}

fun initKoin(
     appDeclaration: KoinAppDeclaration? = null
) {
    startKoin {
        includes(appDeclaration)
        logger(LoggerForIOS())
        modules(
            dataModules +
            domainModules +
            uiModules
        )
    }
}



