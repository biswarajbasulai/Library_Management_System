# Library Management System

A web-based library management system built with Java 8, Servlets, JSP, and Oracle Database.

## Prerequisites
- Java 8
- Oracle Database
- Apache Tomcat
- Maven

## Setup
1. Configure Oracle Database with `sql/oracle_schema.sql`
2. Update `src/main/java/com/library/util/DBConnection.java` with your Oracle credentials
3. Run `mvn clean install`
4. Deploy to Tomcat

## Features
- Book search
- Availability checking
- Overdue book tracking
