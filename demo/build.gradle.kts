plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.projectlombok:lombok:1.18.28")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("net.datafaker:datafaker:2.4.1")
	implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation ("org.springframework.boot:spring-boot-starter-data-rest")
	implementation ("org.springframework.boot:spring-boot-starter-jdbc")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
