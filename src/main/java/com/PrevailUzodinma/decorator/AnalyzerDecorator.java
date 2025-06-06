package com.PrevailUzodinma.decorator;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.strategy.LogAnalyzerStrategy;

import java.util.List;

public abstract class AnalyzerDecorator implements LogAnalyzerStrategy {
    protected final LogAnalyzerStrategy wrappedAnalyzer;

    public AnalyzerDecorator(LogAnalyzerStrategy wrappedAnalyzer) {
        this.wrappedAnalyzer = wrappedAnalyzer;
    }

    @Override
    public List<LogEntry> analyze(List<LogEntry> logs) {
        // Call the analyze method of the wrapped analyzer and get the filtered logs
        List<LogEntry> filteredLogs = wrappedAnalyzer.analyze(logs);

        // Optionally add any additional behavior or summary logic here
        // For example, we could add a summary feature (based on user input or context)

        return filteredLogs;
    }
}
