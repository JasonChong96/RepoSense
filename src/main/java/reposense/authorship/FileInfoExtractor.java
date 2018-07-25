import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.function.Predicate;
    private static final String MATCH_GROUP_FAIL_MESSAGE_FORMAT = "Failed to match the %s group for:\n%s";
    private static final Predicate<String> FILE_DELETED_PREDICATE = Pattern.compile(
            "^deleted file mode [\\d]{6}\n").asPredicate();
    private static final Predicate<String> NEW_EMPTY_FILE_PREDICATE = Pattern.compile(
            "^new file mode [a-zA-Z0-9\n. ]*$").asPredicate();

        logger.info("Extracting relevant file infos " + config.getLocation() + "...");
        final PathMatcher ignoreGlobMatcher = getIgnoreGlobMatcher(config.getIgnoreGlobList());
            // file deleted, is binary file or is new and empty file, skip it
            if (FILE_DELETED_PREDICATE.test(fileDiffResult) || fileDiffResult.contains(BINARY_FILE_SYMBOL)
                    || NEW_EMPTY_FILE_PREDICATE.test(fileDiffResult)) {

            if (shouldIgnore(filePath, ignoreGlobMatcher)) {
                continue;
            }

        final PathMatcher ignoreGlobMatcher = getIgnoreGlobMatcher(config.getIgnoreGlobList());
                if (shouldIgnore(relativePath, ignoreGlobMatcher)) {
                    continue;
     * Returns true if {@code ignoreGlobMatcher} matchers the file path at {@code name}.
    private static boolean shouldIgnore(String name, PathMatcher ignoreGlobMatcher) {
        return ignoreGlobMatcher.matches(Paths.get(name));
            logger.severe(String.format(MATCH_GROUP_FAIL_MESSAGE_FORMAT, "file path", fileDiffResult));
            logger.severe(String.format(MATCH_GROUP_FAIL_MESSAGE_FORMAT, "line changed", linesChanged));

    /**
     * Returns a {@code PathMatcher} that matches any file paths which satisfy any one of the glob patterns in
     * {@code ignoreGlobList}.
     */
    private static PathMatcher getIgnoreGlobMatcher(List<String> ignoreGlobList) {
        String globString = "glob:{" + String.join(",", ignoreGlobList) + "}";
        return FileSystems.getDefault().getPathMatcher(globString);
    }