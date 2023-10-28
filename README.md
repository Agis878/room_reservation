
![rooms_reservation(1)](https://github.com/Agis878/room_reservation/assets/126699089/49b6604c-becf-43be-abef-ef0903712c36)


# Room Reservation Project

## Description

The Room Reservation Project is a system that allows you to reserve available rooms for a specific date. Users,
after logging in, can make reservations, edit their reservations, or delete them.
The system administrator has full access to the reservation list and the ability to generate reports.

## Technologies Used

The Room Reservation project was implemented using the following technologies:

1. **Programming Language:** Java
2. **Framework:** Spring Boot
3. **Security:** Spring Security
4. **Database:** MySQL
5. **Frontend:**
   - JSP (JavaServer Pages): Enables the generation of dynamic content and integration with Java code on the server side.
   - CSS: UI styling.
6. **Dependency Management**
   - Maven: A tool for managing dependencies and building a project.
7. **Testing Frameworks:**
   - JUnit: A Java framework for writing and running unit tests.
   - Mockito: A mocking framework for Java that allows the creation of mock objects in tests.

## Features

1. **Registration and Login**
   - User registration
   - Login panel for authenticated users

3. **Adding Reservations**
   - Checking room availability for a selected date through the list of reservations assigned to a specific room.
   - Users can add reservations by creating new objects of the Reservation class with the appropriate data and assigning
     them to the appropriate rooms and users.

4. **Editing and Canceling Reservations**
   - The system allows editing reservation parameters, such as hours and room, through appropriate forms or editing
     functions.

5. **Administrative Panel**
   - The system administrator has full access to the list of reservations and the ability to generate reports.

6. **Validation and Verification**
   - The system validates entered data, such as dates and times, to ensure they are correct.
   - Validation of room availability before confirming a reservation checks if the room is available at the selected
     time before creating a new reservation.

7. **Authentication and Authorization**
   - Implementation of user authentication and registration mechanisms before room reservation.
   - Granting administrator role with appropriate permissions to users with this role.

## Unit Tests

To ensure the correctness of the project, unit tests were written using the JUnit framework and the Mockito library.

### Repository Tests

- Verification of the correctness of actions on the user and reservation repository.

### Service Tests

- Unit tests for the reservation management service, including tests for adding, editing and verifying the behavior of
  methods for various user reservation scenarios.

### Controller Tests

- Tests for the user controller.



# Projekt Rezerwacji Sal


## Opis

Projekt Rezerwacji Sal jest systemem umożliwiającym zarezerwowanie dostępnych sal na określony termin. Użytkownicy, 
po zalogowaniu, mają możliwość dokonywania rezerwacji, edycji swoich rezerwacji lub ich usuwania. 
Administrator systemu ma pełny dostęp do listy rezerwacji oraz możliwość generowania raportów.

## Wykorzystane Technologie

Projekt Rezerwacji Sal został zrealizowany przy użyciu następujących technologii:

1. **Język Programowania:** Java
2. **Framework:** Spring Boot
3. **Zabezpieczenia:** Spring Security
4. **Baza Danych:** MySQL
5. **Frontend:**
   - JSP (JavaServer Pages): Umożliwia generowanie dynamicznej treści oraz integrację z kodem Java po stronie serwera.
   -  CSS: Stylizacja interfejsu użytkownika.
6. **Zarządzanie Zależnościami**
   - Maven: Narzędzie do zarządzania zależnościami i budowy projektu.
7. **Frameworki Testowe:**
   - JUnit: Framework dla języka Java do pisania i uruchamiania testów jednostkowych.
   - Mockito: Framework do tworzenia mocków w testach jednostkowych.



## Funkcjonalności

1. **Rejestracja i Logowanie**
    - Rejestracja użytkownika
    - Panel logowania dla zalogowanych użytkowników

3. **Dodawanie Rezerwacji**
    - Sprawdzanie dostępności sali w wybranym terminie poprzez listę rezerwacji przypisanych do danej sali.
    - Użytkownicy mogą dodawać rezerwacje, tworząc nowe obiekty klasy Reservation z odpowiednimi danymi i przypisując je
      do odpowiednich sal i użytkowników.
    

4. **Edycja i Anulowanie Rezerwacji**
   - System umożliwia edycję parametrów rezerwacji, takich jak godziny i sala, poprzez odpowiednie formularze lub
     funkcje edycji.

5. **Panel Administracyjny**
    - Administrator systemu ma pełny dostęp do listy rezerwacji oraz możliwość generowania raportów.

6. **Walidacja i Weryfikacja**
    - System przeprowadza walidację wprowadzanych danych, takich jak daty i godziny, aby upewnić się, że są one poprawne.
    - Walidacja dostępności sali przed potwierdzeniem rezerwacji sprawdza, czy sala jest dostępna w wybranym          terminie, zanim zostanie utworzona nowa rezerwacja.

7. **Uwierzytelnianie i Autoryzacja**
    - Implementacja mechanizmu uwierzytelniania i rejestracji użytkowników przed rezerwacją sali.
    - Nadawanie roli administratora z odpowiednimi uprawnieniami użytkownikom o tej roli.

## Testy Jednostkowe

W celu zapewnienia poprawności projektu napisano testy jednostkowe przy użyciu frameworka JUnit oraz biblioteki Mockito.

### Testy Repozytorium

- Weryfikacja poprawności operacji w repozytorium użytkowników i rezerwacji.

### Testy Servisu

- Testy jednostkowe dla usługi zarządzania rezerwacjami, obejmujące testy dodawania, edytowania oraz weryfikację
  zachowania metod w różnych scenariuszach dotyczących rezerwacji użytkownika.

### Testy Kontrolera

- Testy kontrolera użytkownika.


