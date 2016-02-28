import com.mamoreno.systemInfo.os.LinuxInfo
import com.mamoreno.systemInfo.os.MacOsInfo
import com.mamoreno.systemInfo.SystemInfo
import com.mamoreno.systemInfo.SystemGrader

object Main extends App {

	lazy val osName = System.getProperty("os.name")
	lazy val si = getSystemInfo()
	lazy val grade = SystemGrader.grade(si)

	handleSystem(si)

	def getSystemInfo(): Option[SystemInfo] = osName match {
		case "Linux" => Some(LinuxInfo)
		case "Mac OS X" => Some(MacOsInfo)
		case _ => None
	}

	def handleSystem(si: Option[SystemInfo]) = si match {
		case Some(si) => {
			println (s"Handling System '${si.name}'. In this dummy case we just print its values:\n")
			print(si)
			println(s"The system is graded as: $grade")
		}
		case None => {
			println("OS not suported")
			println(s"The system is graded as: $grade")
		}
	}
}
