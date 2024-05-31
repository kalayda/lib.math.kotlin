plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        java.srcDir("src")
    }
}