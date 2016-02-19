package com.mamoreno.systemInfo.os

import scala.language.postfixOps
import sys.process._
import com.mamoreno.systemInfo.SystemInfo

object MacOsInfo extends SystemInfo with MacOsCpuInfoCommand with MacOsMemInfoCommand {
	def apply() = toString
}

trait MacOsCpuInfoCommand {
	
	lazy val cpuInfo = ("sysctl -a" #| "grep machdep.cpu" #| "grep core" !!).split("\n")

	def numProcessors = cpuInfo.filter(x => x.startsWith("machdep.cpu.core_count")).last.split(":").last.trim.toInt
	def cpuCoresPerProcessor = cpuInfo.filter(x => x.startsWith("machdep.cpu.cores_per_package")).last.split(":").last.trim.toInt
}

trait MacOsMemInfoCommand {
	private lazy val memLineValues = getMemLineValues()

	def totalMemory = memLineValues._1 / (1024)
	def usedMemory = memLineValues._2 / (1024)
	def freeMemory = memLineValues._3 / (1024)

	def readMemLine(): String = ("top -l1" #| "grep PhysMem") !!

	def getMemLineValues(): (Double, Double, Double) = {
		lazy val memLine = readMemLine()
		// memLine is like "PhysMem: 7676M used (3263M wired), 515M unused."
		val pattern = "[0-9]+M".r
		val res = pattern.findAllMatchIn(memLine).toList.map(_.toString.replace("M","").toDouble)
		val (used, free) = (res(0), res(2))
		(used+free, used, free)
	}
}