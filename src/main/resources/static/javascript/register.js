const registerForm = document.getElementById('register-form');
const registerUsername = document.getElementById('register-username');
const registerPassword = document.getElementById('register-password');

//a base set of headers that will be included with every request
const headers = {
     'Content-Type':'application/json'
}
const baseUrl = 'http://localhost:8080/api/v1/users'

//handle input from register form
const handleSubmit = async(e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }
    const response = await fetch(`${baseUrl}/register`, {
            method: "POST",
            body: JSON.stringify(bodyObj), //Convert a JavaScript object into a string with JSON.stringify().
            headers: headers
        })
            .catch(err => console.error(err.message))

        const responseArr = await response.json()

        if (response.status === 200){
            window.location.replace(responseArr[0])
        }
    }

    registerForm.addEventListener("submit",handleSubmit) //invoke handleSubmit when user click submit the register form.




// change the response String inside of UserServiceImpl to reflect the redirect URL rather than the success message.
//Using this file as a template, see if you can build out the “login.js” file on your own! Note that you will also
//need to adjust the response String within the UserServiceImpl class to redirect the user to the “home.html” page on a successful login.
//You also may need to research how to create a JavaScript cookie to be able to store the user’s Id for subsequent requests once they’re logged in.