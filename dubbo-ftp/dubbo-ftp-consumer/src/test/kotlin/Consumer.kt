import com.laomd.hw1.FTPService
import org.springframework.context.support.ClassPathXmlApplicationContext
import java.io.File

fun main(args: Array<String>) {
    System.setProperty("java.net.preferIPv6Addresses", "true")
    val context = ClassPathXmlApplicationContext("META-INF/spring/dubbo-ftp-consumer.xml")
    context.start()
    val ftpService = context.getBean("ftpService") as FTPService // get remote service proxy
    while (true) {
        print("ftp> (file name)")
        val filename = readLine()!!.trim()
        if (filename.isNotEmpty()) {
            try {
                val file = File("workdir-2", filename)
                file.deleteOnExit()
                val (remoteAddress, bytes) = ftpService.fetchFile(filename)
                println("fetch from $remoteAddress, size=${bytes.size} bytes")
                file.writeBytes(bytes)
            }
            catch (throwable: Throwable) {
                var words = throwable.message!!.split(' ')
                        .filter { it.isNotEmpty() }
                if (words.size > 20) {
                    words = words.take(20) + listOf("...")
                }
                words.forEach { print(
                                (if (it.length > 30)
                                    "${it.subSequence(0, 27)}..."
                                else it) + " ") }
                println()
            }
        }
    }
}
