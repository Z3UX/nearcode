$(document).ready(function() {
    usersTable();
});

function usersTable() {

    let tableName = $('#table-name');
    let table = $('#table');
    let navigation = $('#navigation');

    navigation.html("");
    tableName.html("List of users:");
    table.html("<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Cars</th></tr>");

    $.ajax({
        url: 'http://localhost:8080/api/user',
        success: successCallback,
    });

    function successCallback(response) {
        response.map(function(object){
            table.append("<tr><td>" + object.id + "</td><td>" +
                object.name +"</td><td>" + object.email + "</td><td>" + object.phone +
                "</td><td><button onclick=\"userCars(" + object.id + ")\" type=\"button\" class=\"btn btn-primary\">Show</button></td>");
        });
    }
}

function userCars(id) {

    let tableName = $('#table-name');
    let table = $('#table');
    let navigation = $('#navigation');

    tableName.html("User " + id + " cars: ");
    table.html("<tr><th>ID</th><th>Brand</th><th>Model</th><th>License Plate</th></tr>");
    navigation.html("<button onclick=\"usersTable()\" type=\"button\" class=\"btn btn-primary\">Go Back</button>");

    $.ajax({
        url: 'http://localhost:8080/api/user/' + id,
        success: successName,
    });

    function successName(response) {
        tableName.html(response.name + " cars: ");
        table.html("<tr><th>ID</th><th>Brand</th><th>Model</th><th>License Plate</th></tr>");
    }

    $.ajax({
        url: 'http://localhost:8080/api/user/' + id + '/car',
        success: successCallback,
    });

    function successCallback(response) {
        response.map(function(object){
            table.append("<tr><td>" + object.id + "</td><td>" +
                object.brand +"</td><td>" + object.model + "</td><td>" + object.licensePlate +
                "</td>");
        });
    }

}