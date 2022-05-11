import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import kotlin.io.path.listDirectoryEntries

// todo extract to config
private const val NOTES_DIR = "/home/bld/notes"

fun initContent(localDate: LocalDate): Iterable<String> {

    val listDirectoryEntries = Paths.get(NOTES_DIR)
        .listDirectoryEntries()
            // todo extract to regex
        .filter { it.fileName.toString().length == 8 } // 'MM-dd.md'
        .sorted()
        .reversed()

    // todo handle no files


    val previousDayTasks = Files.readAllLines(listDirectoryEntries.first())
        .filter {

            // todo extract to a standalone service, that has smarter logic
            // This is a first pass to retain subtasks.
            it.trim().startsWith("- [ ]") || it.trim().startsWith("*")
        }

    // TODO Extract to templating.
    val lines = mutableListOf<String>()
    lines.add("# $localDate Notes")
    lines.add("")
    lines.add("## Today's Tasks:")
    lines.add("")
        lines.add("## Previous Tasks:")
    lines.add("")
    lines.addAll(previousDayTasks)
    return lines
}