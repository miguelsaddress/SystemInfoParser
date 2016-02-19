package com.mamoreno.systemInfo.os

import scala.language.postfixOps
import sys.process._
import com.mamoreno.systemInfo.SystemInfo

object LinuxInfo extends SystemInfo with LinuxCpuInfoCommand with LinuxMemoryInfoCommand {
	def apply() = toString
}

trait LinuxCpuInfoCommand {
	lazy val cpuInfo = ("cat /proc/cpuinfo" !!).split("\n")

	def numProcessors = cpuInfo.filter(x => x.startsWith("processor")).length.toInt
	def cpuCoresPerProcessor = cpuInfo.filter(x => x.startsWith("cpu cores")).last.split(":").last.trim.toInt
}

trait LinuxMemoryInfoCommand {
	lazy val memoryInfo = ("cat /proc/meminfo" !!).split("\n")

	def totalMemory = parseLineFor("MemTotal")
	def freeMemory = parseLineFor("MemFree")
	def usedMemory = totalMemory - freeMemory

	def parseLineFor(s: String): Double = {
		val line = memoryInfo.filter(x => x.startsWith(s)).last.split(":").last.trim.split(" ")(0).toDouble
		line / (1024 * 1024)
	}
}