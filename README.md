# Point of Sale System (IV1350 Seminar 3)

This project is part of the course **IV1350 - Object-Oriented Design**, specifically for **Seminar 3**. It represents a simplified **Point of Sale (POS)** system implemented in Java. The system follows object-oriented principles with a focus on clean architecture, low coupling, and high cohesion.

## Overview

The project is divided into two main tasks:

### Task 1 – Program Implementation

- Implements the basic flow, startup scenario, and alternative flow 3-4b from the seminar 1 specification.
- Replaces the user interface with a `View` class that contains hard-coded calls to the `Controller`.
- Simulates external systems (e.g., Inventory, Accounting) within the integration layer using dummy objects and in-memory data.
- Outputs are printed to `System.out`, including a printed receipt.

### Task 2 – Unit Testing

- Unit tests written using **JUnit 5**.
- Focused on meaningful methods and business logic rather than simple data access.
- Maven is used for dependency management and build automation.

## Technologies Used

- Java
- IntelliJ IDEA
- JUnit 5
- Maven
