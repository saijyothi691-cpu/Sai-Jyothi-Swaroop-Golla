# Sai-Jyothi-Swaroop-Golla
My Projects 
# Sai Jyothi Swaroop G — Full Stack Java Developer

Full Stack Java Developer with 10+ years of experience designing, developing, and supporting enterprise-grade applications across healthcare, finance, insurance, and government sectors. Hands-on expertise in Java, Spring Boot, Spring Batch, Microservices, React.js, Angular, and AWS.

📧 saijyothi691@gmail.com | 📱 669-282-8774 | 🔗 [LinkedIn](https://www.linkedin.com/in/sai-jyothi-s-91423122b/) | 📍 Plano, TX

---

## 🏢 Deloitte — State of Tennessee | Full Stack Java Developer *(Mar 2023 – Present)*

### 🔹 No Touch Processing — Production Support & RCA
Production support for a large-scale, automated eligibility-processing system (public benefits domain), covering the full lifecycle of issue resolution.

- Owned production support end-to-end — issue triage, root-cause identification, fix validation, and safe release — across a distributed pipeline of batch jobs, services, and databases
- Analyzed Java/Spring Batch code and traced business rules across controllers and service layers to resolve defects in workflows spanning renewals, newborn processing, income, resources, household composition, and verification
- Performed structured root-cause analysis using SQL, tracker logs, and Type 1/Type 2 history tables, distinguishing genuine defects from timing issues, upstream data problems, and expected downstream behavior
- Investigated high-impact production defects (renewal signature switches, retro income date errors, Former Foster Care updates, resource-mapping failures) using audit trails and effective-dated history
- Built SQL-based impacted-population analyses to size defect scope and classify issues by severity and business impact
- Authored RCA reports, verification queries, and controlled data-fix recommendations for development, business, and production-support stakeholders

**Tech:** Java, Spring Batch, SQL (Oracle), ServiceNow, Audit/History Tables, EJB

---

### 🔹 CO VCL Summary Daily Batch — End-to-End Development
Designed and built a new Spring Batch job from scratch (`TEDS-1211959`) that generates verification checklist summaries from correspondence data.

- Designed and built a production-grade, restartable Spring Batch service — job configuration, reader, processor, writer, value objects, and data-access components — from concept through production deployment
- Implemented a restartable, chunk-oriented workflow with checkpointing for consistent, recoverable processing of correspondence requests by date
- Developed secure StAX-based XML parsing with external entity and DTD processing disabled, applying least-privilege/injection-defense principles
- Built core logic converting unstructured verification text into standardized VCL codes using reference-table lookups, normalized matching, wildcard handling, and rule-based tie-breakers
- Engineered handling for complex real-world input patterns: financial/burial resources, life insurance, Spanish-language text, duplicates, and inconsistent punctuation
- Implemented composite-key aggregation and de-duplication logic preserving individual/dependent ID lineage, serialized to structured JSON
- Built business-layer persistence logic with logging, exception handling, skip configuration, and operational batch listeners

**Tech:** Java, Spring Batch, StAX XML Parsing, JSON, Oracle SQL

---

### 🔹 Interface Development & Production Support
Development and support for inbound/outbound interfaces connecting the eligibility system (TEDS) with federal, state, and partner systems.

- Developed and maintained interface batches using Spring Batch (readers, processors, writers, tasklets, partition mappers)
- Built record-level validation for inbound interfaces — identifiers, formats, required fields, effective dates
- Contributed to MMIS-related interfaces: eligibility outbound, runout, demographic, and address processing
- Worked on federal verification interfaces: **SDX, Bendex, SVES, SOLQ, TALX**
- Supported **DOH Vital Records, USPS, AVS, FFM** interfaces
- Processed fixed-length, XML, and delimited file formats via mapping configuration files
- Built outbound processing logic: record selection, validation, file generation, and status tracking
- Led production issue analysis for interface failures using SQL, batch logs, staging tables, and audit history

**Tech:** Java, Spring Batch, XML/Fixed-Length File Processing, SQL, EJB

---

## 🏢 Blue Cross Blue Shield | Full Stack Java Developer *(Jun 2022 – Feb 2023)*
- Designed React.js and Angular 8 UIs for insurance claims and member portal serving 1M+ policyholders
- Implemented Microservices architecture with Spring Boot and Docker Compose; OpenAPI/Swagger contracts across 10+ services
- Built CI/CD pipeline (Git/Bitbucket, Bamboo) achieving 98% build success across 50+ weekly deployments
- Managed MongoDB collections for 2M+ member records; optimized MySQL stored procedures

---

## 🏢 Visa | Full Stack Java Developer *(Jun 2021 – Jun 2022)*
- Built React.js + Spring Boot payment applications processing 10M+ daily transactions
- Implemented ABAC authorization and OKTA (MFA, SSO) for 50,000+ enterprise users across 12 countries
- Designed OIDC and SAML-based SSO flows
- Built CI/CD pipeline (Docker, Jenkins, GitHub Actions, AWS ECS), reducing deployment time from 4 hours to 25 minutes
- Leveraged AWS Kinesis and Lambda for real-time payment stream processing

---

## 🛠️ Technical Skills

| Category | Skills |
|---|---|
| **Languages** | Java (8, 11), JavaScript, TypeScript, C, C++ |
| **Frontend** | React.js/Redux, Angular 2/8, HTML5, CSS3, Bootstrap, SASS |
| **Backend** | Spring Boot, Spring MVC, Spring Security, Spring Batch, Hibernate, JPA, Microservices, EJB |
| **Web Services** | REST, SOAP, JAX-RS, JAX-WS, JSON, XML, OpenAPI/Swagger, Kafka, RabbitMQ |
| **Databases** | MySQL, Oracle 11g/12c, PostgreSQL, MongoDB, SQL Server, Redis |
| **Cloud / DevOps** | AWS (EC2, S3, Lambda, ECS, Kinesis), Kubernetes, Docker, Terraform, Jenkins, CI/CD |
| **Testing** | JUnit, Mockito, Jest, Cucumber, SonarQube |
| **Certifications** | AWS Certified Developer – Associate, Oracle Certified Java SE 8 Programmer |

---

## 🎓 Education
- **M.S. Computer Science** — William Jessup University, Rocklin, CA (2017)
- **B.E. Mechanical Engineering** — JNTUK, India (2013)
