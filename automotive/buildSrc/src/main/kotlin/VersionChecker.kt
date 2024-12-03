object VersionChecker {
    fun checkVersions() {
        val gradleVersion = "8.1.1"
        val agpVersion = "8.1.1"
        val kotlinVersion = "1.9.10"
        
        require(isCompatible(gradleVersion, agpVersion)) {
            "Gradle $gradleVersion 与 AGP $agpVersion 不兼容"
        }
    }
    
    private fun isCompatible(gradleVersion: String, agpVersion: String): Boolean {
        return gradleVersion.startsWith(agpVersion.substringBefore('.'))
    }
} 