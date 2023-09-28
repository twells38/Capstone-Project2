console.log('connected');

 const headers={
              'Content-Type': 'application/json'
          }

// create a function that make a get request to retrieve all lost pets from database
    async function getAllLostPets(){
       await fetch('http://localhost:8080/api/v1/lostPets/allLostPets', {
              method:"GET",
              header: headers
        })
         .then(response => response.json())
         .then(data => lostPetCards(data))
         .catch(err => console.error(err))
    }


 //create lostPetCard function to display to landing page
    const showAllLostPets = document.getElementById('allLostPets')
    const lostPetCards = array =>{
          showAllLostPets.innerHTML = '';
          array.forEach(obj =>{
          let petCard = document.createElement('div');
          petCard.classList.add('m-2');
          petCard.innerHTML = `
                         <div class="card">
                         <div class="card-content">

                         <p>Pet Type: ${obj.petType}</p>
                         <p>Pet Name: ${obj.petName}</p>
                         <p>Gender: ${obj.gender}</p>
                         <p>Last Seen: ${obj.lastSeen}</p>
                         <p>Contact: ${obj.contactEmail}</p>
                         <p>Date: ${obj.date}</p>
                         </div>
                         `
              showAllLostPets.append(petCard);
          })
    }

// invoke getAllLostPets function
    getAllLostPets();

/*********************************************************************************************/

//create a function that make a get request to retrieve all found pets from database
    async function getAllFoundPets(){
          await fetch('http://localhost:8080/api/v1/foundPets/allFoundPets',{
                 method: "GET",
                 headers: headers
          })
          .then(response => response.json())
          .then(data => foundPetCards(data))
          .catch(err => console.error(err))
    }

//create foundPetCards function to display all found pets
    const showAllFoundPets = document.getElementById('allFoundPets');
    const foundPetCards = array =>{
           showAllFoundPets.innerHTML = '';
           array.forEach(obj =>{
             let petCard = document.createElement('div');
             petCard.classList.add('m-2');
             petCard.innerHTML =`
             <div class="card">
               <div class="card-content">
               <p>Pet Type: ${obj.petType}</p>
               <p>Gender: ${obj.gender}</p>
               <p>Last Seen: ${obj.found}</p>
               <p>Contact: ${obj.contactEmail}</p>
               <p>Date: ${obj.date}</p>
             </div>
             `
             showAllFoundPets.append(petCard);
           })
    }
 // invoke getAllLostPets function
        getAllFoundPets();