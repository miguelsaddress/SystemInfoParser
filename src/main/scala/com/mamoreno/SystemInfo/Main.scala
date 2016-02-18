import com.mamoreno.SystemInfo.os.LinuxInfo

object Main extends App {

	lazy val osName = System.getProperty("os.name")
	getCpuInfo()

	def getCpuInfo() = osName match {
		case "Linux" => print(LinuxInfo())
		case _ => print(osName + " not supported")
	}
}

// Main.main(Array())