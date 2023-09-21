let loginForm = document.getElementById('login-form');
let usernameLogin = document.getElementById('username-login');
let passwordLogin = document.getElementById('password-login');

const headers ={
   'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/v1/users';
const handleSubmitLogin = async(e)=>{
 e.preventDefault();

 let bodyObj ={
 username:usernameLogin.value;
 password:passwordLogin.value;
 }
 const response  = await fetch(`${baseUrl}/login`,{
  method: 'POST',
  body:JSON.stringify(bodyObj),
  headers: headers
 })
 .catch(err=> console.error(err.message));

 const responseArr = await response.json();
 if(response.status === 200){
  document.cookie = `userId=${responseArr[1]}`
  window.location.replace(responseArr[0])
 }
}

loginForm.addEvenListener('submit', handleSubmitLogin); //invoke handleSubmitLogin() when user click submit the login form
