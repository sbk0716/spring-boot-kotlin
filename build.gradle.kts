/**
 * Kotlin DSL build script
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Apply the Kotlin Gradle plugin
 * Configures the plugin dependencies for this project.
 *
 * https://kotlinlang.org/docs/gradle.html#plugin-and-versions
 */
plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.allopen") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("kapt") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

/**
 * Declaring repositories
 * Configures the repositories for this project.
 * Executes the given configuration block against the RepositoryHandler for this project.
 * Maven Central is a popular repository hosting open source libraries for consumption by Java projects.
 *
 * https://docs.gradle.org/current/userguide/declaring_repositories.html
 */
repositories {
	mavenCentral()
}

/**
 * Declaring dependencies
 * Configures the dependencies for this project.
 * Executes the given configuration block against the DependencyHandlerScope for this project.
 *
 * implementation: Adds a dependency to the 'implementation' configuration.
 * developmentOnly: Adds a dependency to the 'developmentOnly' configuration.
 * runtimeOnly: Adds a dependency to the 'runtimeOnly' configuration.
 * testImplementation: Adds a dependency to the 'testImplementation' configuration.
 * https://docs.gradle.org/current/userguide/declaring_dependencies.html
 */
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(module = "mockito-core")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
}

/**
 * Kotlin Compiler options
 * https://spring.pleiades.io/spring-boot/docs/current/reference/html/features.html#features.kotlin.null-safety
 * https://kotlinlang.org/docs/gradle.html#compiler-options
 */
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

/**
 * Using Gradle Kotlin DSL with JUnit
 * useJUnitPlatform(): Specifies that JUnit Platform should be used to discover and execute the tests.
 */
tasks.withType<Test> {
	useJUnitPlatform()
}