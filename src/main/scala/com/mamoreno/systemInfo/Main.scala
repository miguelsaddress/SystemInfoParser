import com.mamoreno.systemInfo.os.LinuxInfo
import com.mamoreno.systemInfo.os.MacOsInfo

object Main extends App {

	lazy val osName = System.getProperty("os.name")
	getCpuInfo()

	def getCpuInfo() = osName match {
		case "Linux" => print(LinuxInfo())
		case "Mac OS X" => print(MacOsInfo())
		case _ => print(osName + " not supported")
	}
}