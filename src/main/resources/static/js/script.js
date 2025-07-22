function buyProperty() {
        alert("Redirecting to Buy Property page...");
        window.location.href = "/buy";
    }

    function rentProperty() {
        alert("Redirecting to Rent Property page...");
        window.location.href = "/rent";
    }

    function filterProperties() {
        let category = document.getElementById("category").value;
        alert("Filtering properties: " + category);
    }