

function updateCounter() {
    const maxlength = 100;
    const inputField = document.getElementById('texto');
    const counterDisplay = document.getElementById('contador');
    const remaining = maxlength - inputField.value.length;

    counterDisplay.textContent = `Caracteres restantes: ${remaining}`;


    if (inputField.value.length > maxlength) {
        inputField.value = inputField.value.substring(0, maxlength);
    }
}

async function fetchWithAuth(url, options = {}, retry = true) {
    const accessToken = localStorage.getItem("accessToken");
    const headers = {
        ...(options.headers || {}),
        "Authorization": "Bearer " + accessToken,
        "Content-Type": "application/json"
    };

    const response = await fetch(url, { ...options, headers });

    if (response.status === 401 && retry) {
        // Try to refresh the token
        const refreshed = await tryRefreshToken();
        if (refreshed) {
            return fetchWithAuth(url, options, false); // Retry once
        } else {
            logout();
            throw new Error("Session expired");
        }
    }

    return response;
}

async function tryRefreshToken() {
    try {
        const res = await fetch("http://localhost:8080/auth/refresh-token", {
            method: "POST",
            credentials: "include"
        });

        if (!res.ok) {
            return false;
        }
        const data = await res.json();

        localStorage.setItem("accessToken", data.accessToken);
        return true;
    } catch {
        return false;
    }
}

function logout() {
    fetchWithAuth("http://localhost:8080/auth/logout", {
        method: "POST",
        credentials: "include"
    }).finally(() => {
        localStorage.removeItem("accessToken");
        window.location.href = "/login";
    });
}
