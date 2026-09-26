package com.swaroop.batchdemo.processor;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Converts free-text verification descriptions into standardized codes.
 * <p>
 * This mirrors a common production pattern: verification requests often arrive
 * as loosely structured, human-entered text ("Proof of income", "proof of
 * income - self employed", etc.) that needs to be normalized against a
 * reference table before it can be aggregated or reported on reliably.
 * <p>
 * The reference table below is illustrative sample data only — a real
 * implementation would typically load this from a database reference table
 * rather than hard-coding it.
 */
@Component
public class DescriptionCodeMapper {

    /** code -> canonical description, keyed by normalized keyword. */
    private final Map<String, String[]> referenceTable = new LinkedHashMap<>();

    public DescriptionCodeMapper() {
        // keyword -> {code, canonical description}
        referenceTable.put("income", new String[]{"VER-INC", "Income Verification"});
        referenceTable.put("resource", new String[]{"VER-RES", "Resource Verification"});
        referenceTable.put("burial", new String[]{"VER-BUR", "Burial Resource Verification"});
        referenceTable.put("residence", new String[]{"VER-RSD", "Proof of Residence"});
        referenceTable.put("lives with", new String[]{"VER-RSD", "Proof of Residence"});
        referenceTable.put("household", new String[]{"VER-HH", "Household Composition Verification"});
        referenceTable.put("identity", new String[]{"VER-ID", "Identity Verification"});
        referenceTable.put("insurance", new String[]{"VER-INS", "Insurance / Life Insurance Verification"});
    }

    /**
     * Normalizes and matches a raw description string against the reference
     * table, returning a {@code {code, canonicalDescription}} pair.
     * Falls back to an "UNKNOWN" code when no keyword matches, mirroring how
     * production systems flag unrecognized description patterns for manual
     * review rather than silently dropping them.
     */
    public String[] resolve(String rawDescription) {
        String normalized = normalize(rawDescription);

        for (Map.Entry<String, String[]> entry : referenceTable.entrySet()) {
            if (normalized.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return new String[]{"UNKNOWN", "Unrecognized Verification Type: " + rawDescription.trim()};
    }

    private String normalize(String raw) {
        return raw
                .toLowerCase()
                .replaceAll("[\\.,;:!?()\"]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
