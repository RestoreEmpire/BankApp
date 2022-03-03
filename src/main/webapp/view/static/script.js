function disableContent(elementId) {
    if (document.getElementById(elementId).disabled == true) {
        document.getElementById(elementId).disabled = false;
    }
    else{
        document.getElementById(elementId).disabled = true;
        document.getElementById(elementId).value = "";
    }
}

function disableKey(elementId,buttonId) {
    if(document.getElementById(elementId).value.length > 0) {
        document.getElementById(buttonId).disabled = false;
    }
    else {
        document.getElementById(buttonId).disabled = true;
    }
}