package sgd

import java.io.File

object Settings {

  def discoveryDataPaths(): Array[String] = {
    val projectDirectory = new java.io.File(".").getCanonicalPath
    val dataDirectory = new File(projectDirectory, "resources/rcv1rcv2aminigoutte").getPath.toString

    val subDirectories = (new File(dataDirectory))
      .listFiles
      .filter(_.isDirectory)
      .map(_.getPath.toString)

    val filesToRead = subDirectories
      .map(x => (new File(x)).listFiles)
      .map(x => x.filter(_.isFile).map(_.getPath.toString))
      .flatten

    return filesToRead
  }


  val nTrain = 23149
  val nNodes = None

}
