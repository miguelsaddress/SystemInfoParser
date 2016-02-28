package com.mamoreno.systemInfo

import com.mamoreno.systemInfo.SystemInfo
/**
 * A system grader takes a System Info object and returns a number that will indicate
 * how good the system is to be sent information.
 * This is just an example, so it does not attend to a scientific heuristic to determine
 * how free/available the system is.
 */
object SystemGrader {
	def grade(si: Option[SystemInfo]) = si match {
		case None => 0
		case Some(si) => {
			val memRatio = si.freeMemory / si.totalMemory
			val totalThreads = si.numProcessors * si.cpuCoresPerProcessor
			memRatio * totalThreads
		} 
	}
}