package com.laomd.hw1.provider

import com.laomd.hw1.FTPService
import org.apache.dubbo.rpc.RpcContext
import java.io.File

import java.text.SimpleDateFormat
import java.util.Date

class FTPServiceImp(
        private val workdir: String) : FTPService {

    override fun fetchFile(name: String): Pair<String, ByteArray> {
        println("[ ${SimpleDateFormat("HH:mm:ss").format(Date())} ] " +
                "$name, request from consumer: ${RpcContext.getContext().remoteAddress}")
        return Pair(RpcContext.getContext().localAddressString,
                File(workdir, name).readBytes())
    }

}
