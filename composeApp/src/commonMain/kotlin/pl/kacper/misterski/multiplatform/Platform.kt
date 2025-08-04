package pl.kacper.misterski.multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform