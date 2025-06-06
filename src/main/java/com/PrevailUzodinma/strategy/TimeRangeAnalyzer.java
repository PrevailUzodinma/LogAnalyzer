package com.PrevailUzodinma.strategy;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.singleton.LogConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeRangeAnalyzer implements LogAnalyzerStrategy {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final DateTimeFormatter formatter;

    public TimeRangeAnalyzer(LocalDateTime start, LocalDateTime end) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        this.formatter = DateTimeFormatter.ofPattern(pattern);
        this.start = start;
        this.end = end;
    }

    @Override
    public List<LogEntry> analyze(List<LogEntry> entries) {
        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.\n");
            return Collections.emptyList();
        }

        System.out.printf("\nLogs between %s and %s:\n", start.format(formatter), end.format(formatter));
        System.out.println("============================================\n");

        List<LogEntry> filtered = new ArrayList<>();
        int totalLogsInRange = 0;

        for (LogEntry entry : entries) {
            LocalDateTime timestamp = entry.getTimestamp();
            if (!timestamp.isBefore(start) && !timestamp.isAfter(end)) {
                totalLogsInRange++;
                filtered.add(entry);
            }
        }

        System.out.println("\nTotal logs in the specified time range: " + totalLogsInRange);
        System.out.println("============================================================\n");

        boolean found = false;

        for (LogEntry entry : filtered) {
            System.out.println(entry);
            found = true;
        }

        if (!found) {
            System.out.println("\nNo logs found in the specified time range.");
        }

        return filtered;
    }
}
