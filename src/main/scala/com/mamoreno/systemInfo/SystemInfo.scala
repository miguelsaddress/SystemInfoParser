package com.mamoreno.systemInfo

abstract class SystemInfo {
	def name(): String = this.getClass.getSimpleName
	def numProcessors(): Int
	def cpuCoresPerProcessor(): Int
	def totalMemory(): Double
	def freeMemory(): Double
	def usedMemory(): Double

	override def toString = {
		var str = s"Num. Processors [$numProcessors]\n"
		str += s"CPU Cores per Processor [$cpuCoresPerProcessor]\n"
		str += f"Total Memory [$totalMemory%2.2f GB]\n"
		str += f"Used Memory [$usedMemory%2.2f GB]\n"
		str += f"Free Memory [$freeMemory%2.2f GB]\n"
		str
	}
}
