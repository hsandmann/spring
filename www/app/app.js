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
    return false;
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
            // console.log(account);
            document.getElementById('name').innerHTML = account['name'];
            document.getElementById('email').innerHTML = account['email'];
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
    return false;
}

const listAccount = () => {
    const jwt = window.localStorage.getItem('jwt');
    fetch(`${API}/account/list`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }).then(response => {
        response.json().then(accounts => {
            console.log(accounts);
            const accountList = document.getElementById('accountList');
            accounts.forEach(account => {
                const tr = document.createElement('tr');

                const id = document.createElement('td');
                id.innerHTML = account['id'];
                tr.appendChild(id);

                const name = document.createElement('td');
                name.innerHTML = account['name'];
                tr.appendChild(name);

                const birthdate = document.createElement('td');
                birthdate.innerHTML = account['birthdate'];
                tr.appendChild(birthdate);

                const email = document.createElement('td');
                email.innerHTML = account['email'];
                tr.appendChild(email);

                const creation = document.createElement('td');
                creation.innerHTML = account['creation'];
                tr.appendChild(creation);

                accountList.appendChild(tr);
            });
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
