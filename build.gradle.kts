plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.13.2")
    implementation("org.apache.commons:commons-csv:1.10.0")
    implementation(files("bridges-java-3.2.1.jar"))
}

tasks.test {
    useJUnitPlatform()
}