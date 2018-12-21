function assert(condition, message) {
    if (!condition) {
		document.getElementById("list-test").innerHTML  = document.getElementById("list-test").innerHTML + "<li style='color:#f00'>"+ message +" [falhou]</li>"
		
        message = message || "Assertion failed";
        if (typeof Error !== "undefined") {
            throw new Error(message);
        }
        throw message; // Fallback
    }else{
		document.getElementById("list-test").innerHTML  = document.getElementById("list-test").innerHTML + "<li>"+ message +" <span style='color:#0f0'>[passou]</span></li>"
	}
}