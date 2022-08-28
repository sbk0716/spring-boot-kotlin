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
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

/**
 * Kotlin Compiler options
 * https://spring.pleiades.io/spring-boot/docs/current/reference/html/features.html#features.kotlin.null-safety
 * https://kotlinlang.org/docs/gradle.html#compiler-options
 */
tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

/**
 * Using Gradle Kotlin DSL with JUnit
 * useJUnitPlatform(): Specifies that JUnit Platform should be used to discover and execute the tests.
 */
tasks.withType<Test> {
	useJUnitPlatform()
}