package com.laomd.hw1.consumer

import com.laomd.hw1.FTPService
import org.springframework.context.support.ClassPathXmlApplicationContext
import java.io.Console
import java.io.File

fun main(args: Array<String>) {
    System.setProperty("java.net.preferIPv6Addresses", "true")
    val context = ClassPathXmlApplicationContext("META-INF/spring/dubbo-ftp-consumer.xml")
    context.start()
    val ftpService = context.getBean("ftpService") as FTPService // get remote service proxy
    while (true) {
        print("ftp> (file name)")
        val filename = readLine()!!.trim()
        try {
            val file = File("dubbo-ftp-consumer/target/classes", filename)
            file.deleteOnExit()
            val (remoteAddress, bytes) = ftpService.fetchFile(filename)
            println("fetch from $remoteAddress, size=${bytes.size} bytes")
            file.writeBytes(bytes)
        } catch (throwable: Throwable) {
            println(throwable.message)
        }
    }
}
