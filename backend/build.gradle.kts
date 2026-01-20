val ktorVersion = "3.3.3"
val kotlinVersion = "2.3.0"
val logbackVersion = "1.5.25"

plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
    application
    id("io.ktor.plugin") version "3.3.3"
}

group = "jp.trap.algo_event"

version = "0.1.0"

application { mainClass.set("jp.trap.algo_event.ApplicationKt") }

repositories { mavenCentral() }

dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}

// Task to copy frontend build output to resources
val copyFrontend by
        tasks.registering(Copy::class) {
            dependsOn(":frontend:pnpmBuild")
            from(project(":frontend").file("dist"))
            into(layout.buildDirectory.dir("resources/main/static"))
        }

// Task to copy userscript build output to resources
val copyUserscript by
        tasks.registering(Copy::class) {
            dependsOn(":userscript:pnpmBuild")
            from(project(":userscript").file("dist"))
            into(layout.buildDirectory.dir("resources/main/static/userscript"))
        }

// Integration task that builds everything
tasks.register("buildAll") {
    description = "Build frontend, userscript, and backend with all assets"
    dependsOn(copyFrontend, copyUserscript, "classes")
}

tasks.named("processResources") { dependsOn(copyFrontend, copyUserscript) }
