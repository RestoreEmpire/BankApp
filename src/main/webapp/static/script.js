function disableContent(elementId) {
    if (document.getElementById(elementId).disabled == true) {
        document.getElementById(elementId).disabled = false;
    }
    else{
        document.getElementById(elementId).disabled = true;
        document.getElementById(elementId).value = "";
    }
}