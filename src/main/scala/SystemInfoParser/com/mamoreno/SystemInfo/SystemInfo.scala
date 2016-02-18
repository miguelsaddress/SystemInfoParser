package com.mamoreno.SystemInfo

trait SystemInfo {
	def numProcessors: Int
	def cpuCoresPerProcessor: Int
	def totalMemory: Double
	def freeMemory: Double
	def availableMemory: Double
}
