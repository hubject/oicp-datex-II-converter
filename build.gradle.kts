plugins {
    id("java")
    id("jacoco")
    id("checkstyle")
    id("io.freefair.lombok") version ("6.5.1")
    id("maven-publish")
    id("signing")
    id("net.linguica.maven-settings") version("0.5")
}

group = "com.hubject.datex"
version = "1.0.1"

repositories {
    mavenCentral()
}

val jaxb by configurations.creating

configurations {
    jaxb
}

dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")
    implementation("org.mapstruct:mapstruct:1.5.2.Final")
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.slf4j:slf4j-api:2.0.0")

    jaxb("com.sun.xml.bind:jaxb-xjc:2.3.1")
    jaxb("com.sun.xml.bind:jaxb-impl:2.3.1")
    jaxb("org.glassfish.jaxb:jaxb-runtime:2.3.3")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.2.Final")

    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.hubject.datex"
            artifactId = "convert"
            version = version
            from(components["java"])

            pom {
                name.set("OICP to DATEX II converter.")
                url.set("https://github.com/hubject/oicp-datex-II-converter")
                description.set(
                    "Use converters from com.hubject.datex.convert.converters package to convert from " +
                        "the OICP formats to DATEX II TablePublication and StatusPublication."
                )
                licenses {
                    license {
                        name.set("Attribution-ShareAlike 4.0 International")
                        url.set("https://creativecommons.org/licenses/by-sa/4.0/legalcode")
                    }
                }
                developers {
                    developer {
                        id.set("jacek.gajek")
                        name.set("Jacek Gajek")
                        email.set("jacek.gajek@hubject.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/hubject/oicp-datex-II-converter.git")
                    developerConnection.set("scm:git:ssh://github.com:hubject/oicp-datex-II-converter.git")
                    url.set("http://github.com/hubject/oicp-datex-II-converter/tree/main")
                }
            }
        }
    }

    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repos/internal"))
            name = "build-directory"
        }
    }
}

tasks.register("bundle", Jar::class) {
    val dir = layout.buildDirectory.dir("repos/internal/com/hubject/datex/convert/$archiveVersion").get()
    from(dir.asFileTree)
    include("*.jar")
    include("*.pom")
    include("*.sha*")
    include("*.md5")
    include("*.asc")
    destinationDirectory.set(layout.buildDirectory.get())
    archiveFileName.set("converter-$archiveVersion-bundle.jar")
    dependsOn(tasks.publish)
}

signing {
    sign(publishing.publications["maven"])
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    exclude(
                        "**/config/**",
                        "**/dto/**",
                        "**/entity/**",
                        "**/repository/**"
                    )
                }
            }
        )
    )
}

checkstyle {
    toolVersion = "9.0.1"
    configFile = file("config/checkstyle/checkstyle.xml")
}

tasks.withType<Checkstyle> {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.stylesheet = resources.text.fromFile("config/checkstyle/checkstyle.xsl")
    }
}

tasks.register("jaxb") {
    System.setProperty("javax.xml.accessExternalSchema", ("all"))
    doLast {
        ant.withGroovyBuilder {
            "taskdef"(
                "name" to "xjc",
                "classname" to "com.sun.tools.xjc.XJCTask",
                "classpath" to jaxb.asPath
            )
            "xjc"(
                "destdir" to "src/main/java",
                "binding" to "binding.xjb",
                "extension" to "true",
                "schema" to "src/main/resources/soap/SnapshotPull.wsdl",
                "language" to "WSDL"
            ) {
                "arg"("value" to "-XautoNameResolution")
            }
        }
    }
}

tasks.compileJava {
    options.compilerArgs = listOf(
        "-Amapstruct.defaultComponentModel=spring"
    )
}

tasks.build {
    dependsOn("jaxb")
}
