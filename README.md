# Warrant Search System (APCSA Final Project)

## Overview
This project simulates a small-town police system designed to help officers quickly identify active warrants while on patrol. Built as part of an AP Computer Science A final project, the program allows officers to:

- Search license plates for active warrants  
- Identify dangerous suspects  
- Look up warrants by premise (location) code  

The goal is to improve efficiency and awareness during patrol operations.

---

## Features

### License Plate Search
- Input a license plate in the format:  
  CHAR INT INT - CHAR CHAR CHAR  
  Example: G23-HHR  

- Outputs:
  - Driver details
  - Warrant status
  - Crime information (if applicable)
  - "DANGEROUS" flag if a weapon was involved

#### Example Output (With Warrant)
    License plate: G23-HHR
    DRIVER: MALE
    WITH FOLLOWING WARRANT:
    CODE: 442 (SHOPLIFTING – PETTY THEFT ($950 & UNDER))
    DANGEROUS

#### Example Output (No Warrant)
    License plate: G23-HHR
    NO WARRANT

---

### Premise Code Search
- Input a 3-digit premise code (e.g., 101)
- Displays all active warrants associated with that location

#### Example Output (With Warrants)
    Premise Code: 101
    6 Local Warrants:
    Warrant 1 - G23-HHR
    Warrant 2 - G23-HHG
    Warrant 3 - G23-HHH
    Warrant 4 - G23-HHD
    Warrant 5 - G23-HHS
    Warrant 6 - G23-HHA

- User can then input a license plate to view full warrant details

#### Example Output (No Warrants)
    Premise Code: 105
    NO LOCAL WARRANTS

---

## How It Works
- Stores warrant data including:
  - License plate
  - Driver information
  - Crime code and description
  - Dangerous status
- Supports:
  - License plate lookup
  - Premise code grouping
- Uses arrays or ArrayLists for data management

---

## Technologies Used
- Java
- Arrays / ArrayLists
- Conditional logic
- User input handling

---

## Scenario Context
You play as a police officer in a town of ~2,000 civilians with 50 active warrants. Your partner, Officer Lego, encourages you to use your coding skills to streamline warrant searches during patrol.
