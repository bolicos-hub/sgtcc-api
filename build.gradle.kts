import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.asciidoctor.convert") version "1.5.8"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "io.notbronken"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

extra["snippetsDir"] = file("build/generated-snippets")
val openApiVersion = "1.6.9"
//val springCloudVersion = "2022.0.0-M2"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

	/* Spring Boot */
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
//	implementation("org.springframework.boot:spring-boot-starter-mail")
//	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")

	/* Open API */
	implementation("org.springdoc:springdoc-openapi-webflux-ui:${openApiVersion}")
	implementation("org.springdoc:springdoc-openapi-data-rest:${openApiVersion}")
	implementation("org.springdoc:springdoc-openapi-kotlin:${openApiVersion}")
	implementation("org.springdoc:springdoc-openapi-security:${openApiVersion}")

	/* Jackson Serialization for Kotlin */
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	/* Kotlin Reactor and Coroutines */
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j")

	/* Liquibase Migrations */
	implementation("org.liquibase:liquibase-core")

	/* Databases = H2 e Postgres */
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

//	runtimeOnly("io.micrometer:micrometer-registry-new-relic")
//	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	/* Tests */
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-webtestclient")
}
configurations.all {
	exclude(module = "spring-boot-starter-web")
}


//dependencyManagement {
//	imports {
//		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
//	}
//}

noArg {
	annotation("jakarta.persistence.Entity")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	project.property("snippetsDir")?.let {
		outputs.dir(it)
	}
}

tasks.asciidoctor {
	project.property("snippetsDir")?.let {
		inputs.dir(it)
	}
	dependsOn(tasks.test)
}

tasks.jar {
	enabled = false
}