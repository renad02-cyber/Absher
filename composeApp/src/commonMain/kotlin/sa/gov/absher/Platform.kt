package sa.gov.absher

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
