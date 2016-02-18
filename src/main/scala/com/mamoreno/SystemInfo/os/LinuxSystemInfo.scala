package com.mamoreno.SystemInfo.os

import com.mamoreno.SystemInfo.SystemInfo

import scala.language.postfixOps
import sys.process._

object LinuxInfo extends SystemInfo with LinuxCpuInfoCommand with LinuxMemoryInfoCommand {
	def apply() = toString
	override def toString = {
		super[LinuxCpuInfoCommand].toString + super[LinuxMemoryInfoCommand].toString
	}

}

trait LinuxCpuInfoCommand {
	
	lazy val cpuInfo = ("cat /proc/cpuinfo" !!).split("\n")

	def numProcessors = cpuInfo.filter(x => x.startsWith("processor")).length.toInt
	def cpuCoresPerProcessor = cpuInfo.filter(x => x.startsWith("cpu cores")).last.split(":").last.trim.toInt

	override def toString = {
		var str = s"Num. Processors [$numProcessors]\n"
		str += s"CPU Cores per Processor [$cpuCoresPerProcessor]\n"
		str
	}

}

trait LinuxMemoryInfoCommand {
	lazy val memoryInfo = ("cat /proc/meminfo" !!).split("\n")
	def totalMemory = memoryInfo.filter(x => x.startsWith("MemTotal")).last.split(":").last.trim.split(" ")(0).toDouble / (1024 * 1024)
	def freeMemory = memoryInfo.filter(x => x.startsWith("MemFree")).last.split(":").last.trim.split(" ")(0).toDouble / (1024 * 1024)
	def availableMemory = memoryInfo.filter(x => x.startsWith("MemAvailable")).last.split(":").last.trim.split(" ")(0).toDouble / (1024 * 1024)

	override def toString = {
		var str = f"Total Memory [$totalMemory%2.2f GB]\n"
		str += f"Free Memory [$freeMemory%2.2f GB]\n"
		str += f"Available Memory [$availableMemory%2.2f GB]\n"
		str
	}
}