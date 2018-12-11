package pl.michal.olszewski.mongonauka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongonaukaApplication

fun main(args: Array<String>) {
    runApplication<MongonaukaApplication>(*args)
}
