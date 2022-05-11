import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.createFile
import kotlin.io.path.writeLines

// todo extract to config.
private const val NOTES_DIR = "/home/bld/notes"

fun main(args: Array<String>) {

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    if (args.isEmpty()) {
        println("No command provided. Exit")
        return
    }

    if ("create" == args[0]) {
        println("Create file for today's tasks")

        val localDate = LocalDate.now();

        val formattedDate: String = localDate.format(DateTimeFormatter.ofPattern("MM-dd"))

        val currentDateFile = Paths.get(NOTES_DIR, "$formattedDate.md")

        if (Files.exists(currentDateFile)) {
            println("File already exists, don't create it")
            return
        }

        val content = initContent(localDate)

        currentDateFile.createFile()
        currentDateFile.writeLines(content)
    }
}