var billing_country = document.getElementById("billing_country");
var billing_streetAddress = document.getElementById("billing_streetAddress");
var billing_city = document.getElementById("billing_city");
var billing_state = document.getElementById("billing_state");
var billing_phone = document.getElementById("billing_phone");
var phone_acceptable = document.getElementById("phone_acceptable");

var phone_confirmed = /((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}/;

billing_phone.oninput = foo;

function foo() {
    var x = billing_phone.value;

    if (x.length === 0) {
        phone_acceptable.style.color = 'grey';
    }
    if (!phone_confirmed.test(x) && x.length !== 0) {
        phone_acceptable.style.color = 'red';
    } else if (phone_confirmed.test(x) && x.length !== 0) {
        phone_acceptable.style.color = 'green';
    }
}

function checkFields() {
    var country = billing_country.value;
    var street = billing_streetAddress.value;
    var city = billing_city.value;
    var state = billing_state.value;
    var phone = billing_phone.value;

    if (country.length === 0 || street.length === 0 || city.length === 0 ||
        state.length === 0 || phone.length === 0 || !phone_confirmed.test(phone)) {
        alert("Inputs have errors!");
        return false;
    } else {
        return true;
    }
}