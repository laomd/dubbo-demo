package com.laomd.hw1

interface FTPService {

    fun fetchFile(name: String): Pair<String, ByteArray>
}