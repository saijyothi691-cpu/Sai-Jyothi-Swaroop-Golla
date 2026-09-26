# Verification Summary Batch — Demo Project

A small, self-contained **Spring Batch** demo that illustrates the shape of a
production batch job: reading raw records, normalizing free text into
standardized codes, aggregating duplicates by a composite key, and writing a
structured summary file.

> **Note:** This is an original, generic sample built to demonstrate batch
> processing patterns and coding style. It does not contain any proprietary
> business logic, data, or source code from any employer or client project.

## What it does

1. **Reads** verification request records from `src/main/resources/input/verification-requests.csv`
   (case number, individual ID, free-text description, due date).
2. **Processes** each record by normalizing the description and resolving it
   to a standardized code via a reference-table lookup (`DescriptionCodeMapper`).
3. **Aggregates** records that share the same case + code combination,
   merging distinct individual IDs and keeping the earliest due date
   (`AggregatingSummaryWriter`).
4. **Writes** the final aggregated result to `output/verification-summary.json`
   once the job completes successfully.

## Project structure

```
src/main/java/com/swaroop/batchdemo/
├── BatchDemoApplication.java        # Spring Boot entry point
├── config/
│   ├── BatchConfig.java              # Job/step wiring (reader, processor, writer, fault tolerance)
│   └── JobCompletionNotificationListener.java  # Serializes aggregated results to JSON on completion
├── model/
│   ├── VerificationRequest.java      # Raw CSV row
│   └── VerificationSummary.java      # Aggregated, normalized output record
└── processor/
    ├── DescriptionCodeMapper.java    # Free-text -> standardized code resolution
    ├── VerificationRequestProcessor.java  # Per-item processing step
    └── AggregatingSummaryWriter.java # Cross-chunk aggregation by composite key
```

## Requirements

- Java 17+
- Maven 3.8+

## Build & run

```bash
mvn clean package
java -jar target/verification-summary-batch-demo-1.0.0.jar
```

On success, check `output/verification-summary.json` for the aggregated result.

## Key patterns demonstrated

- Chunk-oriented Spring Batch processing (reader → processor → writer)
- Fault-tolerant step configuration (skip logic for malformed records)
- Reference-table-driven text normalization with a fallback "unknown" bucket
  for unrecognized input, instead of silently dropping unmatched records
- Cross-chunk aggregation using a composite key, with a job-completion
  listener to serialize the final rolled-up result
