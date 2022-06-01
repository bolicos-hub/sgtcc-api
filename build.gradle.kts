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
val springCloudVersion = "2022.0.0-M2"

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
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
	}
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("io.projectreactor.netty:reactor-netty")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.liquibase:liquibase-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("io.micrometer:micrometer-registry-new-relic")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-webtestclient")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
	}
}

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