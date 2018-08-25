package com.laomd.hw1.provider

import org.springframework.context.support.ClassPathXmlApplicationContext

fun main(args: Array<String>) {
    System.setProperty("java.net.preferIPv6Addresses", "true")
    val context = ClassPathXmlApplicationContext("META-INF/spring/dubbo-ftp-provider.xml")
    context.start()
    println("press any key to exit")
    readLine() //
}