plugins {
    id("java")
}

group = "io.github.baiyibs.bilibili-sdk"
version = "0.0.1"
description = "Bilibili Java SDK"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.squareup.okhttp3:okhttp:5.4.0")
    implementation("com.google.code.gson:gson:2.14.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:5.4.0")
}

tasks.test {
    useJUnitPlatform()
    filter {
        failOnNoDiscoveredTests = false
    }
}

tasks.javadoc {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "io.github.baiyibs.bilibili.sdk")
    }
}

tasks.withType<JavaExec> {
    systemProperty("file.encoding", "UTF-8")
}

tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
}