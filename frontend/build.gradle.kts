plugins { base }

val pnpmCommand =
        if (System.getProperty("os.name").lowercase().contains("win")) "pnpm.cmd" else "pnpm"

tasks.register<Exec>("pnpmInstall") {
    description = "Install npm dependencies"
    workingDir = projectDir
    commandLine(pnpmCommand, "install")
    inputs.file("package.json")
    outputs.dir("node_modules")
}

tasks.register<Exec>("pnpmBuild") {
    description = "Build the frontend"
    dependsOn("pnpmInstall")
    workingDir = projectDir
    commandLine(pnpmCommand, "run", "build")
    inputs.dir("src")
    inputs.file("package.json")
    inputs.file("vite.config.ts")
    inputs.file("tsconfig.json")
    inputs.file("index.html")
    outputs.dir("dist")
}

tasks.register<Exec>("pnpmDev") {
    description = "Start development server"
    dependsOn("pnpmInstall")
    workingDir = projectDir
    commandLine(pnpmCommand, "run", "dev")
}

tasks.register<Delete>("pnpmClean") {
    description = "Clean build outputs and dependencies"
    delete("dist", "node_modules")
}

tasks.named("build") { dependsOn("pnpmBuild") }

tasks.named("clean") { dependsOn("pnpmClean") }
