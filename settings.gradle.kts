pluginManagement {
    repositories {
        google()
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

rootProject.name = "Apply_Digital_Test"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":ui")
include(":domain")
include(":data")
