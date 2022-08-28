package com.example.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogApplication

/**
 * main function
 * Run a SpringApplication from the specified source using default settings.
 */
fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args)
}
