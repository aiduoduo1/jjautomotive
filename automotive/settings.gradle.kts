pluginManagement {
    repositories {
        // 添加阿里云镜像
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }

        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal {
            content {
                excludeGroupByRegex("com\\.android.*")
                excludeGroupByRegex("android\\.arch.*")
                excludeGroupByRegex("androidx.*")
            }
        }
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // 配置仓库优先级
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://repo.gradle.org/gradle/libs-releases")
                }
            }
            filter {
                includeGroup("org.gradle")
            }
        }
        // 添加阿里云镜像
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }

        google()
        mavenCentral()
    }
}

rootProject.name = "automotive"
include(":automotive")
include(":musicplayer")
include(":dashboard")
