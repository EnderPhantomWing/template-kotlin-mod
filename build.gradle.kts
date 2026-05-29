import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("net.fabricmc.fabric-loom-remap")
	id("maven-publish")
	id("mod-plugin")
	id("org.jetbrains.kotlin.jvm") version "2.3.21"
}

version = fullProjectVersionName
group = modMavenGroup

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
}

dependencies {
	minecraft("com.mojang:minecraft:${prop("minecraft_version")}")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:${prop("loader_version")}")

	// Fabric API. This is technically optional, but you probably want it anyway.
	modImplementation("net.fabricmc.fabric-api:fabric-api:${prop("fabric_api_version")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${prop("fabric_kotlin_version")}")
}

kotlin {
 	compilerOptions {
 		jvmTarget = JvmTarget.fromTarget(javaVersion.toString())
 	}
}

publishing {
	publications {
		register<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}

	// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
	repositories {
		mavenLocal()
	}
}
