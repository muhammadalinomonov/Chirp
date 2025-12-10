package uz.dev.muhammadali.chrip

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform