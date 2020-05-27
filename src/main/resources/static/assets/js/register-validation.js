// inputs
var register_first_name = document.getElementById("register_first_name");
var register_last_name = document.getElementById("register_last_name");
var register_email = document.getElementById("register_email");
var register_password = document.getElementById("register_password");

//password
var length = document.getElementById("length");
var lowerCase = document.getElementById("a-z");
var upperCase = document.getElementById("Z-A");
var digit = document.getElementById("0-9");

// email
var email_acceptable = document.getElementById("email_acceptable");

// first name
var first_name_length = document.getElementById("first_name_length");

// last name
var last_name_length = document.getElementById("last_name_length");

var passReg = /(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,}/;
var oneDigit = /(?=.*[0-9])/;
var oneLowerCase = /(?=.*[a-z])/;
var oneUpperCase = /(?=.*[A-Z])/;
var allRight = /[0-9a-zA-Z]{5,}/;
var email_confirmed = /[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+/;
var names = /[a-zA-Z]{3,}/;

register_password.oninput = foo;
register_email.oninput = foo1;
register_first_name.oninput = foo2;
register_last_name.oninput = foo3;

function foo() {
    var x = register_password.value;

    if (x.length === 0) {
        digit.style.color = 'grey';
        lowerCase.style.color = 'grey';
        upperCase.style.color = 'grey';
        length.style.color = 'grey';
    }
    if (!oneDigit.test(x) && x.length !== 0) {
        digit.style.color = 'red';
    } else if (oneDigit.test(x) && x.length !== 0){
        digit.style.color = 'green';
    }
    if (!oneLowerCase.test(x) && x.length !== 0) {
        lowerCase.style.color = 'red';
    } else if (oneLowerCase.test(x) && x.length !== 0) {
        lowerCase.style.color = 'green';
    }
    if (!oneUpperCase.test(x) && x.length !== 0) {
        upperCase.style.color = 'red';
    } else if (oneUpperCase.test(x) && x.length !== 0) {
        upperCase.style.color = 'green';
    }
    if (!allRight.test(x) && x.length !== 0) {
        length.style.color = 'red';
    } else if (allRight.test(x) && x.length !== 0) {
        length.style.color = 'green';
    }
}

function foo1() {
    var x = register_email.value;

    if (x.length === 0) {
        email_acceptable.style.color = 'grey';
    }
    if (!email_confirmed.test(x) && x.length !== 0) {
        email_acceptable.style.color = 'red';
    } else if (email_confirmed.test(x) && x.length !== 0) {
        email_acceptable.style.color = 'green';
    }
}

function foo2() {
    var x = register_first_name.value;

    if (x.length === 0) {
        first_name_length.style.color = 'grey';
    }
    if (!names.test(x) && x.length !== 0) {
        first_name_length.style.color = 'red';
    } else if (names.test(x) && x.length !== 0) {
        first_name_length.style.color = 'green';
    }
}

function foo3() {
    var x = register_last_name.value;

    if (x.length === 0) {
        last_name_length.style.color = 'grey';
    }
    if (!names.test(x) && x.length !== 0) {
        last_name_length.style.color = 'red';
    } else if (names.test(x) && x.length !== 0) {
        last_name_length.style.color = 'green';
    }
}

function checkFields() {
    var x = register_email.value;
    var y = register_password.value;
    var f = register_first_name.value;
    var l = register_last_name.value;

    if (!email_confirmed.test(x) || !passReg.test(y) || !names.test(f) || !names.test(l)) {
        alert("Inputs have errors!");
        return false;
    } else if (email_confirmed.test(x) && passReg.test(y) && names.test(f) && names.test(l)) {
        return true;
    }
}
