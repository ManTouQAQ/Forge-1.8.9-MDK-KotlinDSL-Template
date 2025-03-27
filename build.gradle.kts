import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension

buildscript {
    repositories {
        maven("https://files.minecraftforge.net/maven")
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath("net.minecraftforge.gradle:ForgeGradle-Modern:2.1")
    }
}

plugins{
    id("java")
}

// also can use plugins{}
apply(plugin = "net.minecraftforge.gradle.forge")

group = "com.yourname.modid"
version = "1.0"

repositories {
    mavenCentral()
}

configurations {
    runtimeOnly {
        isCanBeResolved = true
    }
}

configure<ForgeExtension> {
    version = "1.8.9-11.15.1.2318-1.8.9"
    runDir = "run"
    mappings = "stable_22"
    makeObfSourceJar = false
}

// if you don't need reobfJar (if (enabled = true) like: Minecraft.getMinecraft() -> Minecraft.func_71410_x())
//tasks.named("reobfJar").configure {
//    enabled = false
//}

tasks.processResources {
    val forgeExtension = project.extensions.getByName<ForgeExtension>("minecraft")

    inputs.property("version", project.version)
    inputs.property("mcversion", forgeExtension.version)

    filesMatching("mcmod.info") {
        expand(mapOf("version" to project.version, "mcversion" to forgeExtension.version))
    }
}

dependencies {
    // you may put jars on which you depend on in ./libs
    // or you may define them like so..
    //compile "some.group:artifact:version:classifier"
    //compile "some.group:artifact:version"

    // real examples
    //compile 'com.mod-buildcraft:buildcraft:6.0.8:dev'  // adds buildcraft to the dev env
    //compile 'com.googlecode.efficient-java-matrix-library:ejml:0.24' // adds ejml to the dev env

    // the 'provided' configuration is for optional dependencies that exist at compile-time but might not at runtime.
    //provided 'com.mod-buildcraft:buildcraft:6.0.8:dev'

    // the deobf configurations:  'deobfCompile' and 'deobfProvided' are the same as the normal compile and provided,
    // except that these dependencies get remapped to your current MCP mappings
    //deobfCompile 'com.mod-buildcraft:buildcraft:6.0.8:dev'
    //deobfProvided 'com.mod-buildcraft:buildcraft:6.0.8:dev'

    // for more info...
    // http://www.gradle.org/docs/current/userguide/artifact_dependencies_tutorial.html
    // http://www.gradle.org/docs/current/userguide/dependency_management.html
}