package pl.michal.olszewski.mongonauka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class MongonaukaApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(MongonaukaApplication::class.java, *args)
        }
    }
}