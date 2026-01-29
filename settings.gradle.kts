pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "pizza-mityushin-shift-2026"
include(":app")
include(":component:theme")
include(":feature:main")
include(":feature:pizza-card")
include(":feature:orders")
include(":feature:basket")
include(":feature:profile")
include(":shared:pizza")
include(":shared:network")
