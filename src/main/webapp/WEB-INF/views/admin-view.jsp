<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zestawienie Rezerwacji</title>
</head>
<body>

<h2>Zestawienie Rezerwacji</h2>

<form action="/zestawienie" method="post">

    <label for="reservationType">Rodzaj Rezerwacji:</label>
    <input type="radio" name="reservationType" value="aktywne" id="aktywne"> <label for="aktywne">Aktywne</label>
    <input type="radio" name="reservationType" value="zakonczone" id="zakonczone"> <label for="zakonczone">Zakończone</label>
    <input type="radio" name="reservationType" value="wszystkie" id="wszystkie" checked> <label for="wszystkie">Wszystkie</label>

    <br/><br/>

    <label for="userSelection">Wybierz Użytkownika:</label>
    <input type="radio" name="userSelectionType" value="wszyscy" id="wszyscy" checked> <label for="wszyscy">Wszyscy</label>
    <input type="radio" name="userSelectionType" value="wybierz" id="wybierz"> <label for="wybierz">Wybierz z listy:</label>

    <!-- Dodaj rozwijaną listę tylko w przypadku, gdy wybrano "Wybierz z listy" -->
    <select name="selectedUser" id="selectedUser" style="display:none;">
        <!-- Tutaj możesz dodać dynamicznie generowane opcje na podstawie listy użytkowników -->
        <option value="1">Użytkownik 1</option>
        <option value="2">Użytkownik 2</option>
        <!-- Dodaj więcej opcji według potrzeb -->
    </select>
    <br/><br/>



    <button type="submit">Generuj Zestawienie</button>
    <br/><br/>

</form>

<form action="/generuj-raport-datowy" method="post">

    <br/><br/>

    <label for="dateFrom">Data Od:</label>
    <input type="date" name="dateFrom" id="dateFrom">

    <label for="dateTo">Data Do:</label>
    <input type="date" name="dateTo" id="dateTo">

    <br/><br/>
    <button type="submit">Generuj Raport Datowy</button>

</form>

<script>
    document.querySelector('input[name="userSelectionType"]').addEventListener('change', function () {
        const selectedUsers = document.getElementById('selectedUsers');
        selectedUsers.style.display = this.value === 'wybierz' ? 'block' : 'none';
    });

    document.querySelector('input[name="dateSelectionType"]').addEventListener('change', function () {
        const dateFields = document.querySelectorAll('#dateFrom, #dateTo');
        dateFields.forEach(field => {
            field.style.display = this.value === 'wybierzDaty' ? 'block' : 'none';
        });
    });
</script>

</body>
</html>


