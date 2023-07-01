package com.nrecinos.preparcial.utils;

import java.time.Duration;

import org.springframework.stereotype.Component;

@Component
public class DurationManager {
	public Duration parseDurationString(String durationString) {
        String[] parts = durationString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

    // Format a Duration object into a string representation in the format "HH:mm"
    public String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
