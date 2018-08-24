package com.laomd.hw1.consumer

import com.laomd.hw1.FTPService
import org.springframework.context.support.ClassPathXmlApplicationContext
import java.io.File

fun main(args: Array<String>) {
    System.setProperty("java.net.preferIPv6Addresses", "true")
    val context = ClassPathXmlApplicationContext("META-INF/spring/dubbo-ftp-consumer.xml")
    context.start()
    val ftpService = context.getBean("ftpService") as FTPService // get remote service proxy
    while (true) {
        try {
            val filename = readLine()!!.trim()
            val file = File("dubbo-ftp-consumer/target/classes", filename)
            file.deleteOnExit()
            file.writeBytes(ftpService.fetchFile(filename))
        } catch (throwable: Throwable) {
            println(throwable.message)
        }
    }
}
