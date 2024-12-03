const API = 'http://localhost:8080';

const register = () => {
    var email = document.querySelector('input[name="email"]').value;
    var paassword = document.querySelector('input[name="password"]').value;
    var name = document.querySelector('input[name="name"]').value;
    var birthdate = document.querySelector('input[name="birthdate"]').value;
    console.log(birthdate);
    fetch(`${API}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: paassword,
            name: name,
            birthdate: birthdate
        })
    }).then(response => {
        response.json().then(data => {
            // console.log(data['jwt']);
            // armazenda o token no localStorage do browser
            window.localStorage.setItem('jwt', data['jwt']);
            window.location.href = '/';
        });
    }).catch(error => {
        console.error('Error:', error);
    });
}

const info = () => {
    const info = document.getElementById('info');
    info.style.display = "block";
    const jwt = window.localStorage.getItem('jwt');
    fetch(`${API}/account`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }).then(response => {
        response.json().then(account => {
            console.log(account);
            document.getElementById('name').innerHTML = account['name'];
        });
    }).catch(error => {
        console.error('Error:', error);
    });
}

const login = () => {
    var email = document.querySelector('input[name="email"]').value;
    var paassword = document.querySelector('input[name="password"]').value;
    fetch(`${API}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: paassword
        })
    }).then(response => {
        response.json().then(data => {
            // console.log(data['jwt']);
            // armazenda o token no localStorage do browser
            window.localStorage.setItem('jwt', data['jwt']);
            window.location.href = '/';
        });
    }).catch(error => {
        console.error('Error:', error);
    });
}

const logout = () => {
    window.localStorage.removeItem('jwt');
    window.location.href = '/';
}

const init = () => {
    const jwt = window.localStorage.getItem('jwt');
    if (jwt) {
        info();
    }
}
