$(document).ready(function () {
    usersTable();
});

function usersTable() {

    let tableName = $('#table-name');
    let table = $('#table');
    let navigation = $('#navigation');

    navigation.html("");
    tableName.html("List of Users");
    table.html("<tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Cars</th></tr>");

    $.ajax({
        url: window.location.origin + '/api/user',
        success: successCallback,
    });

    function successCallback(response) {
        response.map(function (object) {
            table.append("<tr><td>" + object.id + "</td><td>" +
                object.name + "</td><td>" + object.email + "</td><td>" + object.phone +
                "</td><td><button onclick=\"userCars(" + object.id + ")\" type=\"button\" class=\"btn btn-primary\">Show</button> " +
                "<button onclick=\"addCar(" + object.id + ")\" type=\"button\" class=\"btn btn-primary\">Add</button></td>")
        });
    }
}

function userCars(id) {

    let tableName = $('#table-name');
    let table = $('#table');
    let navigation = $('#navigation');

    tableName.html("User #" + id + " cars");
    table.html("<tr><th>ID</th><th>Brand</th><th>Model</th><th>License Plate</th></tr>");
    navigation.html("<button onclick=\"usersTable()\" type=\"button\" class=\"btn btn-primary\">Go Back</button> " +
        "<button onclick=\"addCar(" + id + ")\" type=\"button\" class=\"btn btn-primary\">Add Car</button>");

    $.ajax({
        url: window.location.origin + '/api/user/' + id,
        success: successName,
    });

    function successName(response) {
        tableName.html("User #" + id + " " + response.name + " cars");
    }

    $.ajax({
        url: window.location.origin + '/api/user/' + id + '/car',
        success: successCallback,
    });

    function successCallback(response) {
        response.map(function (object) {
            table.append("<tr><td>" + object.id + "</td><td>" +
                object.brand + "</td><td>" + object.model + "</td><td>" + object.licensePlate +
                "</td>");
        });
    }

}

function addCar(id) {

    let tableName = $('#table-name');
    let table = $('#table');
    let navigation = $('#navigation');

    tableName.html("Add car to user #" + id);
    table.html("<tr><th>Brand</th><th>Model</th><th>License Plate</th></tr>" +
        "<tr><td><input type=\"text\" class=\"form-control\" id=\"brand\" placeholder=\"Brand\">\n</td>" +
        "<td><input type=\"text\" class=\"form-control\" id=\"model\" placeholder=\"Model\">\n</td>" +
        "<td><input type=\"text\" class=\"form-control\" id=\"licensePlate\" placeholder=\"License Plate\">\n</td>" +
        "<td><button onclick=\"saveCar(" + id + ")\" type=\"button\" class=\"btn btn-primary\">Save</button></td>"
    );
    navigation.html("<button onclick=\"usersTable()\" type=\"button\" class=\"btn btn-primary\">Go Back</button>");

    $.ajax({
        url: window.location.origin + '/api/user/' + id,
        success: successName,
    });

    function successName(response) {
        tableName.append(" " + response.name);
    }

}

function saveCar(id) {

    let car = {brand: $('#brand').val(), model: $('#model').val(), licensePlate: $('#licensePlate').val()};

    $.ajax({
        method: "POST",
        url: window.location.origin + "/api/user/" + id + "/car/",
        data: JSON.stringify(car),
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            alert("Car saved with ID #" + response.id);
            usersTable();
        },
        error: function (xhr) {
            alert("Error " + xhr.responseJSON.status + ": " + xhr.responseJSON.title);
        }
    });

}