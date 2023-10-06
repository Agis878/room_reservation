
![room-reservation](https://github.com/Agis878/room_reservation/assets/126699089/ebf38754-3adc-429f-b405-00e0ddb415f3)

# Room Reservation Project
The Room Reservation Project is a system that allows you to reserve available rooms for a specific date. Users,
After logging in, they can make reservations, edit their reservations or delete them.
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
   -    JSP (JavaServer Pages): Umożliwia generowanie dynamicznej treści oraz integrację z kodem Java po stronie serwera.
   -  CSS: Stylizacja interfejsu użytkownika.
6. **Zarządzanie Zależnościami**
   - Maven: Narzędzie do zarządzania zależnościami i budowy projektu.




## Funkcjonalności

1. **Rejestracja i Logowanie**
    - Rejestracja użytkownika
    - Panel logowania dla zalogowanych użytkowników

3. **Dodawanie Rezerwacji**
    - Sprawdzanie dostępności sali w wybranym terminie poprzez listę rezerwacji przypisanych do danej sali.
    - Użytkownicy mogą dodawać rezerwacje, tworząc nowe obiekty klasy Reservation z odpowiednimi danymi i przypisując je do odpowiednich sal i użytkowników.
    

4. **Edycja i Anulowanie Rezerwacji**
    - System umożliwia edycję parametrów rezerwacji, takich jak godziny i sala, poprzez odpowiednie formularze lub funkcje edycji.

5. **Panel Administracyjny**
    - Administrator systemu ma pełny dostęp do listy rezerwacji oraz możliwość generowania raportów.

6. **Walidacja i Weryfikacja**
    - System przeprowadza walidację wprowadzanych danych, takich jak daty i godziny, aby upewnić się, że są one poprawne.
    - Walidacja dostępności sali przed potwierdzeniem rezerwacji sprawdza, czy sala jest dostępna w wybranym terminie, zanim zostanie utworzona nowa rezerwacja.

7. **Uwierzytelnianie i Autoryzacja**
    - Implementacja mechanizmu uwierzytelniania i rejestracji użytkowników przed rezerwacją sali.
    - Nadawanie roli administratora z odpowiednimi uprawnieniami użytkownikom o tej roli.

## Instrukcja Uruchomienia

1. **Instalacja zależności**
    - Upewnij się, że posiadasz zainstalowaną odpowiednią wersję języka programowania i frameworka użytego w projekcie.
    - Zainstaluj wymagane biblioteki i narzędzia, korzystając z pliku `requirements.txt` (jeśli projekt korzysta z Pythona).
    - Zainstaluj zależności frameworka i inne narzędzia według dokumentacji.

2. **Konfiguracja Bazy Danych**
    - Skonfiguruj połączenie z bazą danych zgodnie z ustawieniami projektu.


3. **Uruchomienie Aplikacji**
    - Uruchom aplikację, korzystając z odpowiednich komend lub skryptów.

4. **Uzyskanie Dostępu**
    - Zarejestruj się lub zaloguj jako administrator lub użytkownik, aby uzyskać dostęp do pełnych funkcji.


